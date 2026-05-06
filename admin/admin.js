// FILTRO PARTICIPANTES
const participantes = () => {
    const cards = document.querySelectorAll('#filtro-principal .filtro-card');
    const pills = document.querySelectorAll('#filtro-status-wrap .pill');

    const linhas = document.querySelectorAll('#tabela-participantes tbody tr');
    const busca = document.getElementById('busca-input-participante');

    const filtroStatusWrap = document.getElementById('filtro-status-wrap');

    let filtroPrincipal = 'todos';
    let filtroStatus = '';

    function filtrarParticipantes() {
        linhas.forEach(linha => {
            const coffee = linha.dataset.coffee;
            const projeto = linha.dataset.projeto;
            const status = linha.dataset.status || '';
            const texto = linha.innerText.toLowerCase();

            let matchPrincipal = true;
            if (filtroPrincipal === 'coffee') matchPrincipal = coffee === 'sim';
            if (filtroPrincipal === 'projeto') matchPrincipal = projeto === 'sim';
            if (filtroPrincipal === 'sem-projeto') matchPrincipal = projeto === 'nao';

            const matchStatus = filtroStatus === '' || status === filtroStatus;
            const matchBusca = texto.includes(busca.value.toLowerCase());

            linha.style.display = matchPrincipal && matchStatus && matchBusca ? '' : 'none';
        });
    }

    cards.forEach(card => {
        card.addEventListener('click', () => {
            cards.forEach(c => c.classList.remove('ativo'));
            card.classList.add('ativo');
            filtroPrincipal = card.dataset.filtro;

            if (filtroPrincipal === 'projeto') {
                filtroStatusWrap.classList.remove('hidden');
            } else {
                filtroStatusWrap.classList.add('hidden');
                filtroStatus = '';
                pills.forEach(p => p.classList.remove('ativo'));
                pills[0].classList.add('ativo');
            }

            filtrarParticipantes();
        });
    });

    pills.forEach(pill => {
        pill.addEventListener('click', () => {
            pills.forEach(p => p.classList.remove('ativo'));
            pill.classList.add('ativo');
            filtroStatus = pill.dataset.status;
            filtrarParticipantes();
        });
    });

    busca.addEventListener('input', filtrarParticipantes);
};


// FILTRO PALESTRANTES
const palestrantes = () => {
    const cards = document.querySelectorAll('#filtro-palestrantes .filtro-card');
    const rows = document.querySelectorAll('#tabela-palestrantes tbody tr');
    const busca = document.getElementById('busca-input-palestrante');

    let filtroAtivo = '';

    function filtrarPalestrantes() {
        rows.forEach(row => {
            const status = row.dataset.status || '';
            const texto = row.innerText.toLowerCase();

            const matchStatus = filtroAtivo === '' || status === filtroAtivo;
            const matchBusca = texto.includes(busca.value.toLowerCase());

            row.style.display = matchStatus && matchBusca ? '' : 'none';
        });
    }

    cards.forEach(card => {
        card.addEventListener('click', () => {
            cards.forEach(c => c.classList.remove('ativo'));
            card.classList.add('ativo');
            filtroAtivo = card.dataset.filtro;
            filtrarPalestrantes();
        });
    });

    busca.addEventListener('input', filtrarPalestrantes);
};


// INICIAR TUDO
document.addEventListener('DOMContentLoaded', () => {
    // 1. Inicia filtros de participantes se a tabela existir
    if (document.querySelector('#tabela-participantes')) {
        participantes();
    }

    // 2. Inicia filtros de palestrantes se a tabela existir
    if (document.querySelector('#tabela-palestrantes')) {
        palestrantes();
    }

    // 3. Carrega os dados do Dashboard se os campos de resumo existirem
    if (document.getElementById('dash-total-inscritos')) {
        carregarDadosDashboard();
    }
});

// Função para buscar dados do Spring Boot (API)
async function carregarDadosDashboard() {
    try {
        console.log("Chamando dados do dashboard..."); // Para você ver no F12 se funcionou
        
        // Simulação de dados (Trocaremos pela URL do Java depois)
        const dadosSimulados = {
            total: 150,
            coffee: 110,
            projeto: 55
        };

        document.getElementById('dash-total-inscritos').innerText = dadosSimulados.total;
        document.getElementById('dash-total-coffee').innerText = dadosSimulados.coffee;
        document.getElementById('dash-total-projeto').innerText = dadosSimulados.projeto;

    } catch (erro) {
        console.error("Erro ao buscar dados do banco:", erro);
    }
}