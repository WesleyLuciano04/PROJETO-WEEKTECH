// Validação do Formulário de Inscrição de Participante
document.addEventListener ('DOMContentLoaded', function() 
{
    // Verifica se o modo escuro estava ativado na última visita
    const modoEscuroSalvo = localStorage.getItem('modoEscuro');
    if (modoEscuroSalvo === 'true') {
        // Aplica o modo escuro
        document.body.classList.add("modo-escuro");
        const footer = document.querySelector('footer');
        if (footer) footer.classList.add("modo-escuro");
        const sobreCards = document.querySelectorAll('.sobre-card');
        sobreCards.forEach(card => card.classList.add("modo-escuro"));
        
        const btn = document.querySelector('.navegacao button');
        if (btn) btn.textContent = "Claro";
    }

    const formulario = document.getElementById('form-inscricao-participante');
    const temProjetoCheckbox = document.getElementById('tem-projeto');
    const dadosProjeto = document.getElementById('dados-projeto');

    // Mostrar/Ocultar campos de projeto conforme checkbox
    if (temProjetoCheckbox && dadosProjeto) {
        temProjetoCheckbox.addEventListener('change', function() {
            if (this.checked) {
                dadosProjeto.style.display = 'block';
            } else {
                dadosProjeto.style.display = 'none';
            }
        });
    };

    // Validação ao submeter o formulário
    if (formulario) {
        formulario.addEventListener('submit', function(e) {
            e.preventDefault();

            // Limpar mensagens de erro anteriores
            limparErros();

            // Validar campos
            const erros = validarFormulario();

            // Se houver erros, exibir e não submeter
            if (erros.length > 0) {
                exibirErros(erros);
                return false;
            }

            // Se tudo estiver OK, submeter
            alert('Formulário validado com sucesso!');
            // Descomentar a linha abaixo para enviar o formulário
            // this.submit();
        });
    };
});
// Função para validar todos os campos
function validarFormulario() {
    const erros = [];

    // Validar campos pessoais obrigatórios
    const nome = document.getElementById('participante-nome').value.trim();
    const ra = document.getElementById('participante-ra').value.trim();
    const curso = document.getElementById('participante-curso').value;
    const semestre = document.getElementById('participante-serie').value;

    if (!nome) {
        erros.push('Nome completo é obrigatório');
    }

    if (!ra) {
        erros.push('RA é obrigatório');
    }

    if (!curso) {
        erros.push('Curso é obrigatório');
    }

    if (!semestre) {
        erros.push('Semestre é obrigatório');
    }

    // Validar campos de projeto (se marcado)
    const temProjeto = document.getElementById('tem-projeto').checked;
    if (temProjeto) {
        const nomeProjeto = document.getElementById('projeto-nome').value.trim();
        const descricaoProjeto = document.getElementById('projeto-descricao').value.trim();

        if (!nomeProjeto) {
            erros.push('Nome do projeto é obrigatório (você marcou que quer apresentar um projeto)');
        }

        if (!descricaoProjeto) {
            erros.push('Descrição do projeto é obrigatória (você marcou que quer apresentar um projeto)');
        }
    }

    return erros;
}

// Função para exibir erros na tela
function exibirErros(erros) {
    // Criar container de erros se não existir
    let containerErros = document.getElementById('container-erros');
    if (!containerErros) {
        containerErros = document.createElement('div');
        containerErros.id = 'container-erros';
        const formulario = document.getElementById('form-inscricao-participante');
        formulario.parentNode.insertBefore(containerErros, formulario);
    }

    // Adicionar estilos ao container
    containerErros.style.backgroundColor = '#fee';
    containerErros.style.border = '2px solid #c00';
    containerErros.style.borderRadius = '4px';
    containerErros.style.padding = '15px';
    containerErros.style.marginBottom = '20px';
    containerErros.style.fontFamily = 'Arial, sans-serif';

    // Limpar conteúdo anterior
    containerErros.innerHTML = '';

    // Adicionar título
    const titulo = document.createElement('h3');
    titulo.textContent = '⚠️ Erros encontrados:';
    titulo.style.margin = '0 0 10px 0';
    titulo.style.color = '#c00';
    containerErros.appendChild(titulo);

    // Adicionar lista de erros
    const lista = document.createElement('ul');
    lista.style.margin = '0';
    lista.style.paddingLeft = '20px';

    erros.forEach(erro => {
        const item = document.createElement('li');
        item.textContent = erro;
        item.style.marginBottom = '5px';
        item.style.color = '#333';
        lista.appendChild(item);
    });

    containerErros.appendChild(lista);

    // Rolar até o container de erros
    containerErros.scrollIntoView({ behavior: 'smooth', block: 'start' });
}

// Função para limpar mensagens de erro
function limparErros() {
    const containerErros = document.getElementById('container-erros');
    if (containerErros) {
        containerErros.remove();
    }
}

// Função para modo escuro
function modoEscuro() 
{
    // Alterna a classe modo-escuro no body (abordagem mais limpa)
    document.body.classList.toggle("modo-escuro");
    
    // Alterna a classe no footer
    const footer = document.querySelector('footer');
    if (footer) {
        footer.classList.toggle("modo-escuro");
    }
    
    // Alterna a cla de sobre evento
    const sobreCards = document.querySelectorAll('.sobre-card');
    sobreCards.forEach(card => {
        card.classList.toggle("modo-escuro");
    });

    const btn = document.querySelector('.navegacao button');
    if (document.body.classList.contains("modo-escuro")) 
    {
        btn.textContent = "Claro";
    } 
    else 
    {
        btn.textContent = "Escuro";
    }
    
    const isDark = document.body.classList.contains("modo-escuro");
    localStorage.setItem('modoEscuro', isDark ? 'true' : 'false');
}