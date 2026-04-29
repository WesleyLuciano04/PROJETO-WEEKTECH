const windowChat = document.getElementById('window-chat');
const btnChat = document.getElementById('btnChat');
const boxChat = document.getElementById('box-chat');
const inputChat = document.getElementById('user-input');

//objeto - fluxos e respostas do bot
const botConfig =
{
    welcomeMessage: 'Olá, sou o Bot da Tech Week. Como posso te ajudar hoje?',
    options: [''],

    flows:
    {

    },

    responses:
    [

    ]
};

//para abrir e fechar o chatBot
btnChat.addEventListener('click', () =>
{
    windowChat.classList.toggle('hidden');
});

//para enviar a mensagem ao clicar enter
inputChat.addEventListener('keypress', (event) =>
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
    const userMessage = inputChat.value.trim();
    if (!userMessage)
    {
        return null;
    }

    //mensagem que o usuário envia
    appendMessage('Você: ', userMessage);
    inputChat.value = '';      //limpa o campo do input

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
    msg.innerHtml = `<strong>${sender}</strong>${message}`;
    boxChat.appendChild(msg);
    boxChat.scrollTop = boxChat.scrollHeight;
}