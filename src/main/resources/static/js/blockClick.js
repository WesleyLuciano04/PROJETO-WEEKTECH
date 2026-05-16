document.querySelectorAll('form').forEach(function (form) {
    form.addEventListener('submit', function () {
        const btn = form.querySelector('.blockClick');
        if (!btn) return;

        // espera um tick para o main.js rodar a validação primeiro
        setTimeout(function () {
            const temErros = document.querySelectorAll('[id^="container-erros"]').length > 0;

            if (temErros) {
                btn.disabled = false;
                btn.textContent = btn.getAttribute('data-texto') || 'Enviar';
            } else {
                btn.disabled = true;
                btn.textContent = 'Enviando...';
            }
        }, 50);
    });
});