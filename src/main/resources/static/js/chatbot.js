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
        suporte:
        [
            { question: 'Claro, qual é seu nome?' , key: "nome"},
            { question: 'Olá {nome}. Qual é a sua dúvida?', key: "duvida"},
            { question: 'Anotado {nome}. Nossa equipe vai te retornar pelo whatsapp, pode me informar seu número?', key: 'telefone'},
            { question: 'Obrigado(a)! Em breve entraremos em contato.', key: "fim", isFinal: true},
        ],
    },

    responses:
    [
        //data, horário ou local do evento
        { pattern: /(data)/i, response: '📆 O evento irá ocorrer nos dias 01, 02 e 03 de Junho.'},
        { pattern: /(horario|horário|horarios)/i, response: '🕒 O evento começa as 19:00hr e termina as 22:00hr.', action: 'scrollToProgramacao'},
        { pattern: /(endereco|local|endereço)/i, response: '📍Unicesumar Londrina<br>Av. Santa Mônica, 450.', action: 'scrollToLocal'},

        //cronograma: palestras os projetos
        { pattern: /(palestrantes|palestrante)/i, response: 'Confira nessa seção, os palestrantes já confirmados.', action: 'scrollToPalestrantes'},
        { pattern: /(cronograma|palestras)/i, response: 'Nessa seção, você pode conferir toda a programação do evento.', action: 'scrollToProgramacao'},
        { pattern: /(quero palestrar|ser palestrante|enviar palestra)/i, response: 'Ficamos felizes com seu interesse! Você pode enviar sua proposta de palestra através <a href="/inscricao-palestrante">deste formulário</a>.' },
        { pattern: /(projeto|trabalho|apresentar)/i, response: 'Para apresentar um projeto, no momento da inscrição o usuário deve fazer check-in no campo: "Quero apresentar um projeto"'},

        //patrocinadores
        { pattern: /(patrocinio|patrocinar)/i, response: 'Para formalizar o patrocínio e conhecer as cotas disponíveis, entre em contato diretamente com a nossa coordenação pelo telefone/WhatsApp: (43) 99996-1905.'},
        { pattern: /(patrocinadores)/i, response: 'Confira nessa seção, os nossos patrocinadores.', action: 'scrollToPatrocinadores'},
        
        //sobre o que se trata a techWeek
        { pattern: /(sobre|tech|week)/i, response: 'A Tech Week reúne alunos, professores e profissionais para discutir inovação e tecnologia. O foco desta edição é Inteligência Artificial aplicada na prática.'},

        //como fazer a inscrição
        { pattern: /(inscricao|inscrever|cadastro|participar)/i, response: 'Você pode realizar sua inscrição clicando no botão "Faça sua Inscrição" no topo da página ou <a href="/inscricao-participante">clicando aqui</a>.' },
        //confirmação de presença
        { pattern: /(certificado|horas|presenca|checkin)/i, response: 'A presença será registrada via QR Code ao final de cada palestra. O usuário deve ler o qr code através da página do evento, na aba de login.'},
        //coffee break
        { pattern: /(comida|lanche|coffee|cafe)/i, response: 'O Coffee Break é opcional. Em caso de interesse no momento da inscrição, marque o campo: "Participar do Coffee Break". Importante estar ciente que haverá uma taxa colaborativa, que será cobrada presencialmente em sala de aula.'},
        //meios de contato
        { pattern: /(instagram|redes|social|contato)/i, response: 'Siga a @unicesumarlondrina no Instagram para novidades em tempo real ou fale pelo WhatsApp: (44) 9139-6999.' },

        //agradecimento
        { pattern: /(obrigado|obrigada)/i, response: 'Nós que agradecemos! Caso precise de mais informações é só me pedir! 🤗'},

        //dúvidas de custo
        { pattern: /(gratis|gratuito|pago|valor|preco|custo)/i, response: 'A participação no evento é gratuita! O único custo opcional é o Coffee Break, cobrado presencialmente.' },

        { pattern: /(falar com alguem|suporte)/i, response: null, flow: 'suporte'}
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
        appendMessage('TW: ', botResponse); //resposta do bot
    }, 500) //tempo de resposta em milisegundos
}

function appendMessage(sender, message)
{
    const msg = document.createElement('div');
    msg.classList.add('msg-balao');

    const isUser = sender.startsWith('Você');
    msg.classList.add(isUser ? 'msg-usuario' : 'msg-bot');

    if (!isUser)
    {
        msg.innerHTML = `<span class="msg-remetente">${sender}</span>${message}`;
    }
    else
    {
        msg.innerHTML = message;
    }
    
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
            if (response.action === 'scrollToProgramacao')
            {
                scrollToElement('programacao');
            }

            if (response.action === 'scrollToPatrocinadores')
            {
                scrollToElement('patrocinadores');
            }

            if (response.action === 'scrollToPalestrantes')
            {
                scrollToElement('palestrantes');
            }

            if (response.action === 'scrollToLocal')
            {
                scrollToElement('localizacao');
            }

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

        question = question.replace(`{${key}}`, valor);
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

function scrollToElement(id)
{
    const element = document.getElementById(id);
    if(element)
    {
        element.scrollIntoView({behavior: 'smooth', block: 'start'});
    }
}