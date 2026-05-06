# Guia: Implementação do Modo Escuro

## Problemas Encontrados

### 1. **CSS Incompleto**
O problema principal era que a classe `.modoEscuro` no CSS (linha 1224-1228) alterava **apenas o gradiente de fundo**, mas não modificava as cores dos textos, botões, cards e outros elementos. Isso resultava em textos claros (brancos) sobre um fundo escuro, mas com fundos de cards e botões ainda claros, reduzindo o contraste e a legibilidade.

### 2. **Abordagem Fragmentada no JavaScript**
O código JavaScript original adicionava/removia a classe `.modoEscuro` individualmente em múltiplos elementos:
```javascript
const elementos = document.querySelectorAll('body, #sobre, #sobre-evento, ...');
elementos.forEach(el => el.classList.toggle("modoEscuro"));
```
Essa abordagem tem dois problemas:
- **Difícil manutenção**: Se você adicionar novos elementos no HTML, precisará atualizar essa lista manualmente
- **Ineficiente**: Você precisa de regras CSS separadas para cada combinação possível

### 3. **Sem Persistência**
O estado do modo escuro não era salvo. Ao recarregar a página ou navegar para outra página, o tema voltava ao padrão claro.

## Solução Implementada

### 1. **Abordagem Baseada em Classe no Body**
A melhor prática é adicionar/remover uma classe (ex: `.modo-escuro`) apenas no `<body>` e usar seletores CSS para aplicar estilos aos elementos filhos:

**Vantagens:**
- **Centralizado**: Toda a lógica de cores fica no CSS
- **Mais fácil de manter**: Adicionar novos elementos não requer mudanças no JavaScript
- **Código mais limpo**: O HTML e JS ficam mais simples

### 2. **CSS Atualizado**
Adicionamos regras CSS específicas para quando o body tem a classe `.modo-escuro`:

```css
body.modo-escuro {
    background: radial-gradient(circle at center, #0a2342 0%, #050c17 100%);
    color: #e0e0e0; /* Textos claros sobre fundo escuro */
}

body.modo-escuro .sobre-card {
    background: var(--blue-2); /* Fundo escuro para cards */
    border: 1px solid rgba(255, 255, 255, 0.1);
    color: #e0e0e0;
}

body.modo-escuro .sobre-card h3 {
    color: var(--yellow-1); /* Destaque amarelo */
}

/* E assim por diante para outros elementos... */
```

### 3. **Persistência com localStorage**
Agora o estado é salvo no navegador do usuário:

```javascript
// Salva preferência
localStorage.setItem('modoEscuro', isDark ? 'true' : 'false');

// Carrega preferência ao iniciar
const modoEscuroSalvo = localStorage.getItem('modoEscuro');
if (modoEscuroSalvo === 'true') {
    document.body.classList.add("modo-escuro");
    // ...aplica também aos elementos necessários
}
```

### 4. **JavaScript Simplificado**
O código ficou mais limpo e fácil de entender:

```javascript
function modoEscuro() {
    // Alterna apenas no body
    document.body.classList.toggle("modo-escuro");
    
    // Aplica em elementos específicos que precisam
    const footer = document.querySelector('footer');
    if (footer) footer.classList.toggle("modo-escuro");
    
    // Alterna texto do botão
    const btn = document.querySelector('.navegacao button');
    if (document.body.classList.contains("modo-escuro")) {
        btn.textContent = "Claro";
    } else {
        btn.textContent = "Escuro";
    }
    
    // Salva preferência
    const isDark = document.body.classList.contains("modo-escuro");
    localStorage.setItem('modoEscuro', isDark ? 'true' : 'false');
}
```

## Como Funciona Agora

1. **Ao clicar no botão**: A função `modoEscuro()` é chamada
2. **No JavaScript**: A classe `.modo-escuro` é alternada no `<body>`
3. **No CSS**: Todas as regras `body.modo-escuro .elemento` são aplicadas automaticamente
4. **Persistência**: O estado é salvo no `localStorage`
5. **Ao recarregar**: O script verifica o `localStorage` e aplica o tema salvo

## Boas Práticas para Modo Escuro

### Cores Recomendadas
- **Fundo escuro**: Cinza muito escuro ou azul escuro (#0a2342, #050c17)
- **Textos**: Cinza claro (#e0e0e0), não use branco puro (#ffffff) - cansa os olhos
- **Destaques**: Mantenha sua cor de destaque (no caso, amarelo #FFD621)
- **Cards**: Fundo ligeiramente mais claro que o fundo principal

### Contraste
O WCAG (Web Content Accessibility Guidelines) recomenda:
- Pelo menos 4.5:1 de contraste para textos normais
- Pelo menos 3:1 para textos grandes

Sempre teste seu tema escuro com ferramentas de contraste!

### Transições Suaves
Para melhor experiência, adicione transições:
```css
body, .elemento {
    transition: background 0.3s ease, color 0.3s ease;
}
```

## Testando o Modo Escuro

1. Clique no botão "escuro" na navegação
2. Observe que:
   - Fundo fica escuro
   - Textos ficam claros
   - Cards e botões adaptam suas cores
   - Elementos amarelos (destaques) permanecem visíveis
3. Recarregue a página - o tema deve persistir
4. Clique em "Claro" para voltar ao tema claro

## Referências

- [WCAG Contrast Guidelines](https://www.w3.org/WAI/WCAG21/Understanding/contrast-minimum.html)
- [MDN: CSS Variables](https://developer.mozilla.org/en-US/docs/Web/CSS/Using_CSS_custom_properties)
- [MDN: localStorage](https://developer.mozilla.org/en-US/docs/Web/API/Window/localStorage)