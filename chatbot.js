const windowChat = document.getElementById('window-chat');
const btnChat = document.getElementById('btnChat');
const boxChat = document.getElementById('box-chat');
const input = document.getElementById('user-input');

let currentFlow = null;       //detectar se está ou não no fluxo
let flowIndex = 0;            //detectar em que ponto está do fluxo
let flowData = {};            //detectar os dados que está utilizando
let flowHistory = [];         //salvar as informações do usuário

//objeto - fluxos e respostas do bot
const botConfig =
{
    welcomeMessage: 'Olá, sou o Bot da Tech Week. Como posso te ajudar hoje?',
    options: ['Data, Horário ou Local do Evento.', 'Cronograma: Palestras ou Projetos.', 'Palestrantes.', 'Patrocinadores.'],

    flows:
    {

    },

    responses:
    [
        { pattern: /(data)/i, response: '<br>O evento irá ocorrer nos dias 01, 02 e 03 de Junho.'},
        { pattern: /(horario|horário)/i, response: '<br>O evento começa as 19:00hr e termina as 22:00hr.'},
        { pattern: /(endereco|local|endereço)/i, response: '<br>📍Unicesumar Londrina<br>Av. Santa Mônica, 450.'},
        { pattern: /(palestras|palestra)/i, response: null, flow: 'palestra'},
        { pattern: /(projeto|projetos)/i, response: null, flow: 'projeto'},
        { pattern: /(palestrantes|palestrante)/i, response: null, flow: 'palestrante'},
        { pattern: /(patrocinadores)/i, response: null, flow: 'patrocinadores'},
    ]
};

//para abrir e fechar o chatBot
btnChat.addEventListener('click', () =>
{
    windowChat.classList.toggle('hidden');
});

//para enviar a mensagem ao clicar enter
input.addEventListener('keypress', (event) =>
{
    if (event.key === 'Enter')
    {
        event.preventDefault();
        sendMessage();
    }
});

//click no X para fechar a caixa do chat
function closeChat(idEl = '')
{
    const elementChat = document.getElementById(idEl);
    if (elementChat)
    {
        elementChat.click();
    }
}

function sendMessage()
{
    const userMessage = input.value.trim();
    if (!userMessage)
    {
        return null;
    }

    //mensagem que o usuário envia
    appendMessage('Você: ', userMessage);
    input.value = '';      //limpa o campo do input

    setTimeout(() =>
    {
        //o bot recebe o que o usuário escreveu
        const botResponse = getBotResponse(userMessage);
        appendMessage('Fernandinha: ', botResponse); //resposta do bot
    }, 500) //tempo de resposta em milisegundos
}

function appendMessage(sender, message)
{
    const msg = document.createElement('div');
    msg.innerHTML = `<strong>${sender}</strong>${message}`;
    boxChat.appendChild(msg);
    boxChat.scrollTop = boxChat.scrollHeight;
}

let responseHistory = [];
function getBotResponse(input)
{
    const msg = normalizeText(input);

    if(currentFlow != null)
    {
        const step = botConfig.flows[currentFlow][flowIndex] ?? '';
        flowData[step.key] = msg;
        flowIndex ++;

        //chat responde e segue o fluxo
        const next = botConfig.flows[currentFlow][flowIndex];
        if (!next) 
        {
            resetFlow();
            return 'Obrigada, recebi todas as informações!';
        }

        //verificação
        const text = bindQuestionParameter(next.question, next.key);
        if (next.isFinal)
        {
            resetFlow(); //finaliza o fluxo
        }
        return text;
    }

    //verificação se possui histórico, caso não - envia a Saudação
    if (responseHistory.length < 1)
    {
        responseHistory.push(msg);
        return botConfig.welcomeMessage;
    }

    for (let response of botConfig.responses)
    {
        if (response.pattern.test(msg))
        {
            if (response.flow)
            {
                currentFlow = response.flow;
                flowIndex = 0;
                flowData = {};

                return botConfig.flows[currentFlow][flowIndex].question ?? '';
            }

            return response.response;
        }
    };

    let html = 'Desculpe, não entendi. Poderia reformular? <br>Nossas opções: ';
    botConfig.options.forEach(element =>
    {
        html += `<br>➡️${element}`; //retorna as opções
    });

    return html;
}

function resetFlow()
{
    flowHistory.push({tries: flowHistory.length + 1, data: flowData});

    currentFlow = null;
    flowIndex = 0;
    flowData = {};
}

function bindQuestionParameter(question = '')
{
    for (let key of Object.keys(flowData))
    {
        let valor = flowData[key];
        if (key === 'nome')
        {
            valor = caps(valor);
        }

        question = question.replace(`{${key}`, valor);
    }

    return question;
}

//normalização para remover caracteres especiais, números...
function normalizeText(str)
{
    return str
    .normalize("NFD")
    .replace(/[\u0300-\u036f]/g, "")
    .replace(/[^a-zA-Z0-9@.\s]/g, "")
    .toLowerCase();
}

//primeira letra maiúscula, para o nome
function caps(str) 
{
    if (typeof str !== 'string' || str.length === 0) return str;
    return str.charAt(0).toUpperCase() + str.slice(1);
}