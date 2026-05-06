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

document.addEventListener('DOMContentLoaded', () => {
    if (document.querySelector('#tabela-participantes')) {
        participantes();
    }

    if (document.querySelector('#tabela-palestrantes')) {
        palestrantes();
    }
});
