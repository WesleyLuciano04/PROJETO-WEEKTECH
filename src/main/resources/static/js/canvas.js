
    const canvas = document.getElementById('grid-canvas');
    const ctx = canvas.getContext('2d');
    let mouse = { x: canvas.width / 2, y: canvas.height / 2 };
    const stars = [];
    const COUNT = 120;

    function resize() {
        canvas.width = canvas.offsetWidth;
        canvas.height = canvas.offsetHeight;
    }

    function init() {
        stars.length = 0;
        for (let i = 0; i < COUNT; i++) {
            stars.push({
                x: Math.random() * canvas.width,
                y: Math.random() * canvas.height,
                size: Math.random() * 2 + 0.5,
                speed: Math.random() * 0.3 + 0.1,
                color: Math.random() > 0.5 ? 'rgba(255,214,33,' : 'rgba(255,255,255,'
            });
        }
    }

    function draw() {
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        stars.forEach(s => {
            const dx = mouse.x - s.x;
            const dy = mouse.y - s.y;
            const dist = Math.sqrt(dx * dx + dy * dy);
            if (dist < 200) {
                s.x += (dx / dist) * s.speed * 2;
                s.y += (dy / dist) * s.speed * 2;
            } else {
                s.x += (Math.random() - 0.5) * 0.3;
                s.y += (Math.random() - 0.5) * 0.3;
            }
            if (s.x < 0) s.x = canvas.width;
            if (s.x > canvas.width) s.x = 0;
            if (s.y < 0) s.y = canvas.height;
            if (s.y > canvas.height) s.y = 0;
            ctx.beginPath();
            ctx.arc(s.x, s.y, s.size, 0, Math.PI * 2);
            ctx.fillStyle = s.color + '0.9)';
            ctx.fill();
        });
        requestAnimationFrame(draw);
    }

    window.addEventListener('mousemove', e => {
        const rect = canvas.getBoundingClientRect();
        mouse.x = e.clientX - rect.left;
        mouse.y = e.clientY - rect.top;
    });

    window.addEventListener('resize', () => { resize(); init(); });
    resize(); init(); draw();