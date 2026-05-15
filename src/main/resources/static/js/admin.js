const participantes = () => {
    const cards = document.querySelectorAll('#filtro-principal .filtro-card');
    const pills = document.querySelectorAll('#filtro-status-wrap .pill');
    const pillsDia = document.querySelectorAll('#filtro-dia-wrap .pill');

    const linhas = document.querySelectorAll('#tabela-participantes tbody tr');
    const busca = document.getElementById('busca-input-participante');

    const filtroStatusWrap = document.getElementById('filtro-status-wrap');
    const filtroDiaWrap = document.getElementById('filtro-dia-wrap');

    let filtroPrincipal = 'todos';
    let filtroStatus = '';
    let filtroDia = '';

    function filtrarParticipantes() {
        linhas.forEach(linha => {
            const coffee = linha.dataset.coffee;
            const projeto = linha.dataset.projeto;
            const status = linha.dataset.status || '';
            const presenca1 = linha.dataset.presenca1;
            const presenca2 = linha.dataset.presenca2;
            const presenca3 = linha.dataset.presenca3;
            const texto = linha.innerText.toLowerCase();

            let matchPrincipal = true;
            if (filtroPrincipal === 'coffee') matchPrincipal = coffee === 'sim';
            if (filtroPrincipal === 'projeto') matchPrincipal = projeto === 'sim';
            if (filtroPrincipal === 'presenca') {
                matchPrincipal = presenca1 === 'sim' || presenca2 === 'sim' || presenca3 === 'sim';
            }

            const matchStatus = filtroStatus === '' || status === filtroStatus;

            let matchDia = true;
            if (filtroDia === '1') matchDia = presenca1 === 'sim';
            if (filtroDia === '2') matchDia = presenca2 === 'sim';
            if (filtroDia === '3') matchDia = presenca3 === 'sim';

            const matchBusca = texto.includes(busca.value.toLowerCase());

            linha.style.display = matchPrincipal && matchStatus && matchDia && matchBusca ? '' : 'none';
        });
    }

    cards.forEach(card => {
        card.addEventListener('click', () => {
            cards.forEach(c => c.classList.remove('ativo'));
            card.classList.add('ativo');
            filtroPrincipal = card.dataset.filtro;

            if (filtroPrincipal === 'projeto') {
                filtroStatusWrap.classList.remove('hidden');
                filtroDiaWrap.classList.add('hidden');
                filtroDia = '';
                pillsDia.forEach(p => p.classList.remove('ativo'));
                pillsDia[0].classList.add('ativo');
            } else if (filtroPrincipal === 'presenca') {
                filtroDiaWrap.classList.remove('hidden');
                filtroStatusWrap.classList.add('hidden');
                filtroStatus = '';
                pills.forEach(p => p.classList.remove('ativo'));
                pills[0].classList.add('ativo');
            } else {
                filtroStatusWrap.classList.add('hidden');
                filtroDiaWrap.classList.add('hidden');
                filtroStatus = '';
                filtroDia = '';
                pills.forEach(p => p.classList.remove('ativo'));
                pills[0].classList.add('ativo');
                pillsDia.forEach(p => p.classList.remove('ativo'));
                pillsDia[0].classList.add('ativo');
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

    pillsDia.forEach(pill => {
        pill.addEventListener('click', () => {
            pillsDia.forEach(p => p.classList.remove('ativo'));
            pill.classList.add('ativo');
            filtroDia = pill.dataset.dia;
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