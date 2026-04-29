// AGUARDA CARREGAMENTO DA PAG
document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("form-inscricao-palestrante");

  if (!form) return; // segurança

  // FUNÇÃO PARA ERRO
  function erro(msg, e) {
    alert(msg);
    e.preventDefault();
  }

  form.addEventListener("submit", function (e) {
    // PEGAR CAMPOS
    const nome = document.getElementById("palestrante-nome").value.trim();
    const telefone = document
      .getElementById("palestrante-telefone")
      .value.trim();
    const email = document.getElementById("palestrante-email").value.trim();
    const tema = document.getElementById("palestrante-tema").value.trim();
    const briefing = document
      .getElementById("palestrante-briefing")
      .value.trim();
    const duracao = document.getElementById("palestrante-duracao").value;
    const curriculo = document
      .getElementById("palestrante-curriculo-resumo")
      .value.trim();
    const autorizacao = document.getElementById(
      "palestrante-autoriza-divulgacao",
    ).checked;

    // VALIDAÇÃO CAMPOS INDIVIDUAIS
    if (!nome) return erro("Preencha o nome completo!", e);
    if (nome.length < 5) return erro("Digite seu nome completo!", e);

    if (!telefone) return erro("Preencha o telefone!", e);

    // REMOVE TUDO QUE NÃO FOR NUMERO
    const telefoneLimpo = telefone.replace(/\D/g, "");
    if (telefoneLimpo.length < 10) return erro("Digite um telefone válido!", e);

    if (!email) return erro("Preencha o e-mail!", e);

    // VALIDAÇÃO E-MAIL
    const emailValido = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailValido.test(email)) return erro("Digite um e-mail válido!", e);

    if (!tema) return erro("Preencha o tema da palestra!", e);

    if (!briefing) return erro("Preencha o briefing da palestra!", e);

    if (!curriculo) return erro("Preencha o mini currículo!", e);

    // VALIDAÇÃO DURAÇÃO
    if (duracao === "Selecione") {
      return erro("Selecione a duração da palestra!", e);
    }

    const minutos = parseInt(duracao);

    if (isNaN(minutos)) return erro("Duração inválida!", e);

    if (minutos < 40)
      return erro("A palestra deve ter no mínimo 40 minutos!", e);

    if (minutos > 60)
      return erro("A palestra não pode ultrapassar 60 minutos!", e);

    // CHECKBOX
    if (!autorizacao) {
      return erro("Você precisa autorizar a divulgação dos dados!", e);
    }

    // SUCESSO
    alert("Inscrição validada com sucesso!");
  });
});
\