document.querySelectorAll('.blockClick').forEach(function (btn) {
    btn.addEventListener('click', function () {
        this.disabled = true;
        this.textContent = 'Enviando...';
    });
});