# Projeto - Tech Week

<p align="center">
  <img src="imgs/logotw1.png" alt="" width="30%">
  <h1 align="center">Tech Week</h1>
</p> 

<p align="center">
    <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" />
    <img src="https://img.shields.io/badge/HTML-E34F26?style=for-the-badge&logo=html5&logoColor=white" />
    <img src="https://img.shields.io/badge/CSS-%23663399?style=for-the-badge&logo=css&logoColor=%23fffff&color=%231572B6" />
    <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black" />
    <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" />
</p>

## 📌 Índice
1. [Descrição](#-descrição)
2. [Funcionalidades](#-funcionalidades)
3. [Tecnologias Utilizadas](#️-tecnologias-utilizadas)
4. [Demonstração da Página](#-demonstração-da-página)
5. [Deploy](#-deploy)
6. [Como executar o projeto](#-como-executar-o-projeto)
7. [Sobre os Autores](#-sobre-a-autora)

## 📋 Descrição
<p align="justify">
    &emsp;&emsp; A <strong>Tech Week</strong> é a semana de tecnologia da Unicesumar Londrina, um evento interno criado para reunir estudantes, professores e profissionais da área. A edição desse ano conta com o tema 'Inteligência Artificial em Ação: Dados, Inovação e Transformação Digital'. <br>
    &emsp;&emsp; O projeto Tech Week, consiste em uma plataforma web que será responsável por exibir todo o cronograma do evento, desenvolvido por uma equipe de alunos, que tiveram como desafio atuar como uma agência de software. Todos contribuíram para o desenvolvimento, mas cada membro contava com uma responsabilidade específica — detalhada na seção de autores. <br>
    &emsp;&emsp; A plataforma será o ponto de informações de todo o evento. Por meio dela os estudantes podem se cadastrar, podem solicitar apresentar um projeto próprio, e os profissionais que tem interesse em compartilhar seu conhecimento, podem enviar uma proposta de palestra. <br>
    &emsp;&emsp; O projeto foi construída buscando uma plataforma responsiva, visando grande acesso por dispositivos móveis, com foco também em acessibilidade e experiência do usuário.
</p>

## ✨ Funcionalidades

<details>
<summary>🖥️ Interface & Experiência</summary>

- 🎨 **Grid - Canvas** → Fundo animado e interativo com partículas que reagem ao movimento do mouse.
- 🌙 **Botão Tema** → Permite alternar entre tema claro e escuro para maior conforto visual.
- 💾 **Persistência de Tema** → A preferência de tema é salva via localStorage, mantendo a escolha mesmo após fechar e reabrir a página.
- 🃏 **Cards Interativos** → Os cards de cada dia são exibidos recolhidos por padrão e expandem ao passar o mouse, revelando as sessões com horário e local.
- 🧭 **Navegação entre páginas** → Header com navegação entre Home, Participante e Palestrante, presente em todas as páginas do portal.

</details>

<details>
<summary>🤖 Suporte & Informação</summary>

- ❓ **FAQ** → Perguntas frequentes fixas para maior rapidez em encontrar respostas.
- 💬 **ChatBot** → Feito puramente com JS, responde perguntas relacionadas ao evento e encaminha o usuário diretamente a seções específicas da página.

</details>

<details>
<summary>📋 Formulários & Inscrições</summary>

- 📝 **Formulário de Inscrição de Participantes** → Cadastro com validação de campos obrigatórios (nome, RA, curso e semestre), com exibição de erros de forma dinâmica.
- 🗂️ **Inscrição para Apresentação de Projetos** → O participante pode marcar que deseja apresentar um projeto, revelando campos adicionais dinamicamente.
- 🎤 **Formulário de Inscrição de Palestrantes** → Envio de proposta de palestra com validação de dados pessoais, tema, briefing e mini currículo.

</details>

<details>
<summary>🔐 Administração</summary>

- 👤 **Perfil Administrador** → Permite ao responsável acompanhar as inscrições e selecionar as palestras que irão aparecer na página principal.

</details>

---

## 🛠️ Tecnologias Utilizadas

![HTML](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![Spring Boot](https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

| Tecnologia | Uso |
|---|---|
| **HTML5** | Estrutura de todas as páginas do portal, incluindo os formulários de inscrição de participantes e palestrantes. |
| **CSS3** | Estilização personalizada da interface, incluindo o tema claro/escuro, responsividade e os cards interativos. |
| **JavaScript** | Lógica do chatbot, validação dos formulários, animação do canvas interativo, persistência do tema via localStorage e comportamentos dinâmicos da página. |
| **Spring Boot** | Back-end da aplicação, responsável pelo gerenciamento das inscrições e pelo painel do administrador. |
| **MySQL** | Banco de dados relacional utilizado para armazenar as inscrições de participantes, propostas de palestrantes e dados do painel administrativo. |
| **Canvas API** | Renderização do fundo animado de partículas interativas na página principal. |

## 📸 Demonstração da Página
<div align="center">
  <img src="" height="450" controls> <br> <br>
</div>


## 🔗 Deploy

<p align="center">
    <a href="">
        
    </a>
</p>

## 🚀 Como executar o projeto

⚠️ Necessário ter o Git já devidamente instalado, e configurado em seu computador. ⚠️ <br>

Utilizando o git clone, clone o repositório para seu dispositivo local e abra o arquivo **index.html** <br>

1. Acesse uma pasta do seu computador, através do terminal (VSCode, CMD). <br>
*Nessa pasta que o git irá armazenar os arquivos, vindo do repositório.*
2. Utilize: `cd` + (endereço da pasta). Exemplo: cd C:\Users\usuário\documentos\projetos <br>
3. Estando dentro da pasta através do terminal, use o comando: `git clone https://github.com/WesleyLuciano04/PROJETO-WEEKTECH.git`
4. Localize a pasta onde os arquivos foram clonados. <br>
*O git clone baixa o repositório em seu computador, como uma pasta.* <br>
5. Abra a pasta clonada. <br>
6. Abra o arquivo *`index.html`* no navegador. <br>


## 👨‍💻 Sobre os Autores

<p>A equipe é composta por estudantes de Engenharia de Software e Análise e Desenvolvimento de Sistemas da Unicesumar Londrina.</p>

<br>

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/Kafnosof">
        <img src="https://github.com/Kafnosof.png" width="80px" style="border-radius: 50%"/><br>
        <sub><b>Cristian Ferreira</b></sub>
      </a><br>
      <sub>Desenvolvedor Back-end</sub>
    </td>
    <td align="center">
      <a href="https://github.com/DaviAlme1da">
        <img src="https://github.com/DaviAlme1da.png" width="80px" style="border-radius: 50%"/><br>
        <sub><b>Davi Almeida</b></sub>
      </a><br>
      <sub>Designer UI/UX · Back-end</sub>
    </td>
    <td align="center">
      <a href="https://github.com/EmmanuelLucasRM">
        <img src="https://github.com/EmmanuelLucasRM.png" width="80px" style="border-radius: 50%"/><br>
        <sub><b>Emannuel Lucas</b></sub>
      </a><br>
      <sub>Scrum Master</sub>
    </td>
    <td align="center">
      <a href="https://github.com/jotta91">
        <img src="https://github.com/jotta91.png" width="80px" style="border-radius: 50%"/><br>
        <sub><b>João</b></sub>
      </a><br>
      <sub>QA</sub>
    </td>
  </tr>
  <tr>
    <td align="center">
      <a href="https://github.com/niveasofia">
        <img src="https://github.com/niveasofia.png" width="80px" style="border-radius: 50%"/><br>
        <sub><b>Nivea Sofia</b></sub>
      </a><br>
      <sub>Product Owner</sub>
    </td>
    <td align="center">
      <a href="https://github.com/pedr0vis">
        <img src="https://github.com/pedr0vis.png" width="80px" style="border-radius: 50%"/><br>
        <sub><b>Pedro Henrique</b></sub>
      </a><br>
      <sub>Project Manager</sub>
    </td>
    <td align="center">
      <a href="https://github.com/Ryan-Amorin">
        <img src="https://github.com/Ryan-Amorin.png" width="80px" style="border-radius: 50%"/><br>
        <sub><b>Ryan Amorim</b></sub>
      </a><br>
      <sub>Designer UI/UX</sub>
    </td>
    <td align="center">
      <a href="https://github.com/WesleyLuciano04">
        <img src="https://github.com/WesleyLuciano04.png" width="80px" style="border-radius: 50%"/><br>
        <sub><b>Wesley Luciano</b></sub>
      </a><br>
      <sub>Desenvolvedor Back-end</sub>
    </td>
  </tr>
</table>
