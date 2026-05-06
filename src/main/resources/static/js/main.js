// ============================================================
//  Tech Week 2026 — main.js
// ============================================================

document.addEventListener('DOMContentLoaded', function() {

    // ---- Modo escuro salvo ----
    const modoEscuroSalvo = localStorage.getItem('modoEscuro');
    if (modoEscuroSalvo === 'true') {
        document.body.classList.add("modo-escuro");
        const footer = document.querySelector('footer');
        if (footer) footer.classList.add("modo-escuro");
    }

    // ---- Máscara do RA ----
    // Formato: 00000000-2  (8 dígitos + hífen + 1 dígito fixo "2")
    // Máximo: 10 caracteres
    const inputRa = document.getElementById('participante-ra');
    if (inputRa) {
        inputRa.addEventListener('input', function() {
            let valor = this.value.replace(/\D/g, ''); // remove tudo que não é número

            // Limita a 9 dígitos (8 + 1 fixo)
            if (valor.length > 9) valor = valor.slice(0, 9);

            // A partir do 9° dígito fixa o "2" no final
            if (valor.length === 9) {
                valor = valor.slice(0, 8) + '-2';
            } else if (valor.length >= 8) {
                // Quando chegar em 8 dígitos adiciona o hífen
                valor = valor.slice(0, 8) + '-';
            }

            this.value = valor;
        });

        // Impede digitar após os 10 caracteres
        inputRa.addEventListener('keydown', function(e) {
            const teclaPermitida = ['Backspace', 'Delete', 'ArrowLeft', 'ArrowRight', 'Tab'];
            if (this.value.length >= 10 && !teclaPermitida.includes(e.key)) {
                e.preventDefault();
            }
        });
    }

    // ---- Máscara do Telefone ----
    // Formato: (00) 00000-0000  (máximo 15 caracteres)
    const inputTelefone = document.getElementById('palestrante-telefone');
    if (inputTelefone) {
        inputTelefone.addEventListener('input', function() {
            let valor = this.value.replace(/\D/g, ''); // remove tudo que não é número

            // Limita a 11 dígitos (DDD + 9 dígitos)
            if (valor.length > 11) valor = valor.slice(0, 11);

            if (valor.length > 6) {
                valor = '(' + valor.slice(0, 2) + ') ' + valor.slice(2, 7) + '-' + valor.slice(7);
            } else if (valor.length > 2) {
                valor = '(' + valor.slice(0, 2) + ') ' + valor.slice(2);
            } else if (valor.length > 0) {
                valor = '(' + valor;
            }

            this.value = valor;
        });

        // Impede digitar além de (00) 00000-0000 = 15 caracteres
        inputTelefone.addEventListener('keydown', function(e) {
            const teclaPermitida = ['Backspace', 'Delete', 'ArrowLeft', 'ArrowRight', 'Tab'];
            if (this.value.length >= 15 && !teclaPermitida.includes(e.key)) {
                e.preventDefault();
            }
        });
    }

    // ---- Toggle projeto ----
    const temProjetoCheckbox = document.getElementById('tem-projeto');
    const dadosProjeto = document.getElementById('dados-projeto');

    if (temProjetoCheckbox && dadosProjeto) {
        temProjetoCheckbox.addEventListener('change', function() {
            dadosProjeto.style.display = this.checked ? 'block' : 'none';
        });
    }

    // ---- Validação do formulário de participante ----
    const formulario = document.getElementById('form-inscricao-participante');
    if (formulario) {
        formulario.addEventListener('submit', function(e) {
            limparErros('container-erros');
            const erros = validarFormulario();

            if (erros.length > 0) {
                e.preventDefault();
                exibirErros(erros, formulario, 'container-erros');
            }
            // Se não houver erros, o form submete normalmente para o Spring Boot
        });
    }

    // ---- Validação do formulário de palestrante ----
    const formularioPalestrante = document.getElementById('form-inscricao-palestrante');
    if (formularioPalestrante) {
        formularioPalestrante.addEventListener('submit', function(e) {
            limparErros('container-erros-palestrante');
            const erros = validarFormularioPalestrante();

            if (erros.length > 0) {
                e.preventDefault();
                exibirErros(erros, formularioPalestrante, 'container-erros-palestrante');
            }
            // Se não houver erros, o form submete normalmente para o Spring Boot
        });
    }

    // ---- Accordion da programação ----
    document.querySelectorAll('.dia-toggle').forEach(btn => {
        btn.addEventListener('click', () => {
            const expandido = btn.getAttribute('aria-expanded') === 'true';
            const alvo = document.getElementById(btn.getAttribute('aria-controls'));
            const seta = btn.querySelector('[aria-hidden="true"]');

            btn.setAttribute('aria-expanded', String(!expandido));
            if (alvo) alvo.classList.toggle('aberto', !expandido);
            if (seta) seta.textContent = !expandido ? '▲' : '▼';
        });
    });

    // ---- Fade nos alertas de sucesso ----
    document.querySelectorAll('.alerta-sucesso').forEach(el => {
        setTimeout(() => {
            el.style.transition = 'opacity 0.5s';
            el.style.opacity = '0';
            setTimeout(() => el.remove(), 500);
        }, 5000);
    });

});

// ---- Validações do participante ----
function validarFormulario() {
    const erros = [];

    const nome     = document.getElementById('participante-nome')?.value.trim();
    const ra       = document.getElementById('participante-ra')?.value.trim();
    const curso    = document.getElementById('participante-curso')?.value;
    const semestre = document.getElementById('participante-serie')?.value;

    if (!nome)     erros.push('Nome completo é obrigatório.');
    if (!ra)       erros.push('RA é obrigatório.');
    if (!curso)    erros.push('Curso é obrigatório.');
    if (!semestre) erros.push('Semestre é obrigatório.');

    // Valida formato do RA: 00000000-2
    if (ra && !/^\d{8}-2$/.test(ra)) {
        erros.push('RA inválido. O formato deve ser 00000000-2.');
    }

    // Valida campos de projeto se marcado
    const temProjeto = document.getElementById('tem-projeto')?.checked;
    if (temProjeto) {
        const nomeProjeto      = document.getElementById('projeto-nome')?.value.trim();
        const descricaoProjeto = document.getElementById('projeto-descricao')?.value.trim();

        if (!nomeProjeto)      erros.push('Nome do projeto é obrigatório.');
        if (!descricaoProjeto) erros.push('Descrição do projeto é obrigatória.');
    }

    return erros;
}

// ---- Validações do palestrante ----
function validarFormularioPalestrante() {
    const erros = [];

    const nome       = document.getElementById('palestrante-nome')?.value.trim();
    const telefone   = document.getElementById('palestrante-telefone')?.value.trim();
    const email      = document.getElementById('palestrante-email')?.value.trim();
    const tema       = document.getElementById('palestrante-tema')?.value.trim();
    const briefing   = document.getElementById('palestrante-briefing')?.value.trim();
    const duracao    = document.getElementById('palestrante-duracao')?.value;
    const curriculo  = document.getElementById('palestrante-curriculo-resumo')?.value.trim();
    const autorizacao = document.getElementById('palestrante-autoriza-divulgacao')?.checked;

    if (!nome)              erros.push('Nome completo é obrigatório.');
    else if (nome.length < 5) erros.push('Digite seu nome completo.');

    if (!telefone) {
        erros.push('Telefone é obrigatório.');
    } else {
        const telefoneLimpo = telefone.replace(/\D/g, '');
        if (telefoneLimpo.length < 10) erros.push('Digite um telefone válido no formato (00) 00000-0000.');
    }

    if (!email) {
        erros.push('E-mail é obrigatório.');
    } else {
        const emailValido = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailValido.test(email)) erros.push('Digite um e-mail válido.');
    }

    if (!tema)      erros.push('Tema da palestra é obrigatório.');
    if (!briefing)  erros.push('Briefing da palestra é obrigatório.');
    if (!curriculo) erros.push('Mini currículo é obrigatório.');

    if (!duracao || duracao === '' || duracao === 'Selecione') {
        erros.push('Selecione a duração da palestra.');
    } else {
        const minutos = parseInt(duracao);
        if (isNaN(minutos))   erros.push('Duração inválida.');
        else if (minutos < 40) erros.push('A palestra deve ter no mínimo 40 minutos.');
        else if (minutos > 60) erros.push('A palestra não pode ultrapassar 60 minutos.');
    }

    if (!autorizacao) erros.push('Você precisa autorizar a divulgação dos dados.');

    return erros;
}

// ---- Exibir erros (genérico, aceita o form e o id do container) ----
function exibirErros(erros, formulario, containerId) {
    let container = document.getElementById(containerId);
    if (!container) {
        container = document.createElement('div');
        container.id = containerId;
        formulario.parentNode.insertBefore(container, formulario);
    }

    container.style.cssText = `
        background:#fee; border:2px solid #c00; border-radius:4px;
        padding:15px; margin-bottom:20px; font-family:Arial,sans-serif;
    `;

    container.innerHTML = '';

    const titulo = document.createElement('h3');
    titulo.textContent = '⚠️ Erros encontrados:';
    titulo.style.cssText = 'margin:0 0 10px 0; color:#c00;';
    container.appendChild(titulo);

    const lista = document.createElement('ul');
    lista.style.cssText = 'margin:0; padding-left:20px;';
    erros.forEach(erro => {
        const item = document.createElement('li');
        item.textContent = erro;
        item.style.cssText = 'margin-bottom:5px; color:#333;';
        lista.appendChild(item);
    });

    container.appendChild(lista);
    container.scrollIntoView({ behavior: 'smooth', block: 'start' });
}

// ---- Limpar erros (genérico, aceita o id do container) ----
function limparErros(containerId) {
    const container = document.getElementById(containerId);
    if (container) container.remove();
}

// ---- Modo escuro ----
function modoEscuro() {
    document.body.classList.toggle("modo-escuro");

    const footer = document.querySelector('footer');
    if (footer) footer.classList.toggle("modo-escuro");

    const sobreCards = document.querySelectorAll('.sobre-card');
    sobreCards.forEach(card => card.classList.toggle("modo-escuro"));

    const btn = document.querySelector('.navegacao button');
    const isDark = document.body.classList.contains("modo-escuro");

    if (isDark) {
        btn.innerHTML = `<svg id="icon-tema" xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24"
        fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true">
        <circle cx="12" cy="12" r="5"/>
        <line x1="12" y1="1" x2="12" y2="3"/><line x1="12" y1="21" x2="12" y2="23"/>
        <line x1="4.22" y1="4.22" x2="5.64" y2="5.64"/><line x1="18.36" y1="18.36" x2="19.78" y2="19.78"/>
        <line x1="1" y1="12" x2="3" y2="12"/><line x1="21" y1="12" x2="23" y2="12"/>
        <line x1="4.22" y1="19.78" x2="5.64" y2="18.36"/><line x1="18.36" y1="5.64" x2="19.78" y2="4.22"/></svg>`;
    } else {
        btn.innerHTML = `<svg id="icon-tema" xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24"
        fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true">
        <path d="M21 12.79A9 9 0 1 1 11.21 3 7 7 0 0 0 21 12.79z"/></svg>`;
    }

    localStorage.setItem('modoEscuro', isDark ? 'true' : 'false');

    const windowChat = document.getElementById('window-chat');
    if (windowChat) windowChat.classList.toggle("modo-escuro");
}