// ── API Base ─────────────────────────────────────────────────────────────────
const API = '/api';

// ── Toast ─────────────────────────────────────────────────────────────────────
function showToast(msg, type = 'success') {
    const t = document.getElementById('toast');
    t.textContent = msg;
    t.className = `toast ${type} show`;
    setTimeout(() => t.classList.remove('show'), 3500);
}

// ── Preloader ─────────────────────────────────────────────────────────────────
function togglePreloader(show) {
    const p = document.getElementById('preloader');
    if (show) p.classList.add('active');
    else p.classList.remove('active');
}

// ── Broom Helper ──────────────────────────────────────────────────────────────
function toggleBroom(tabId, show) {
    const broom = document.querySelector(`#tab-${tabId} .broom-overlay`);
    if (!broom) return;

    if (show) {
        broom.classList.add('active');
        // Safety: ensure broom dies after 3s
        broom.dataset.safetyTimeout = setTimeout(() => {
            broom.classList.remove('active');
        }, 3000);
    } else {
        clearTimeout(broom.dataset.safetyTimeout);
        broom.classList.remove('active');
    }
}

// ── Sidebar Navigation ────────────────────────────────────────────────────────
function initSidebar() {
    const sidebar = document.querySelector('.sidebar');
    const overlay = document.getElementById('sidebar-overlay');
    const menuToggle = document.getElementById('menu-toggle');

    if (!sidebar || !overlay) {
        console.warn('Sidebar elements not found');
        return;
    }

    function toggleSidebar(show) {
        if (show === undefined) show = !sidebar.classList.contains('open');

        if (show) {
            sidebar.classList.add('open');
            overlay.classList.add('active');
            document.body.style.overflow = 'hidden'; // Block body scroll when menu open
        } else {
            sidebar.classList.remove('open');
            overlay.classList.remove('active');
            document.body.style.overflow = '';
        }
    }

    if (menuToggle) {
        menuToggle.onclick = () => toggleSidebar();
    }

    if (overlay) {
        overlay.onclick = () => toggleSidebar(false);
    }

    document.querySelectorAll('.nav-btn').forEach(btn => {
        btn.addEventListener('click', () => {
            document.querySelectorAll('.nav-btn').forEach(b => b.classList.remove('active'));
            document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));

            btn.classList.add('active');
            const targetTab = btn.dataset.tab;
            const targetEl = document.getElementById('tab-' + targetTab);
            if (targetEl) targetEl.classList.add('active');

            // Close sidebar on mobile after clicking a link
            if (window.innerWidth <= 768) {
                toggleSidebar(false);
            }

            // Hide animation panel on Connect tab
            const animPanel = document.getElementById('anim-panel');
            if (animPanel) {
                animPanel.style.display = targetTab === 'connect' ? 'none' : 'block';
            }
        });
    });
}

// Call sidebar init
initSidebar();

// ── Theme Toggle ──────────────────────────────────────────────────────────────
function toggleTheme() {
    const isLight = document.documentElement.getAttribute('data-theme') === 'light';
    const next = isLight ? 'dark' : 'light';

    if (next === 'light') {
        document.documentElement.setAttribute('data-theme', 'light');
        document.getElementById('theme-icon').textContent = '☀️';
    } else {
        document.documentElement.removeAttribute('data-theme');
        document.getElementById('theme-icon').textContent = '🌙';
    }
    localStorage.setItem('theme-pref', next);
}

// Restore saved theme on load
document.addEventListener('DOMContentLoaded', function () {
    const saved = localStorage.getItem('theme-pref') || 'dark';
    if (saved === 'light') {
        document.documentElement.setAttribute('data-theme', 'light');
        const icon = document.getElementById('theme-icon');
        if (icon) icon.textContent = '☀️';
    }
});





// ── Helpers ───────────────────────────────────────────────────────────────────
function renderTable(tableId, rows, cols, renderRow) {
    const tbody = document.querySelector(`#${tableId} tbody`);
    if (!rows || rows.length === 0) {
        tbody.innerHTML = `<tr class="empty-row"><td colspan="${cols}">No data found.</td></tr>`;
        return;
    }
    tbody.innerHTML = rows.map(renderRow).join('');
}

function statusBadge(status) {
    const cls = status === 'Available' ? 'badge-available' : 'badge-issued';
    return `<span class="badge ${cls}">${status}</span>`;
}

// ── Books ─────────────────────────────────────────────────────────────────────
async function loadBooks() {
    toggleBroom('books', true);
    startDataFlow('fetch'); // Start looping girl toward client

    // Hide table data
    const tbody = document.querySelector('#books-table tbody');
    tbody.style.opacity = '0';

    try {
        const res = await fetch(`${API}/books`);
        const data = await res.json();

        // Wait for the smooth 3.5s animation
        await new Promise(r => setTimeout(r, 3500));

        renderTable('books-table', data, 4, b =>
            `<tr><td>${b.bookId}</td><td>${b.title}</td><td>${b.author}</td><td>${statusBadge(b.status)}</td></tr>`
        );
    } catch {
        showToast('❌ Failed to load books', 'error');
    } finally {
        toggleBroom('books', false);
        tbody.style.opacity = '1';
        stopDataFlow(); // Stop animation
    }
}

document.getElementById('form-add-book').addEventListener('submit', async e => {
    e.preventDefault();
    const body = {
        bookId: document.getElementById('book-id').value.trim(),
        title: document.getElementById('book-title').value.trim(),
        author: document.getElementById('book-author').value.trim(),
    };
    startDataFlow('request'); // Start looping girl toward DB
    try {
        const res = await fetch(`${API}/books`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body),
        });
        const data = await res.json();

        // Ensure Gibli girl completes a smooth pass to DB
        await new Promise(r => setTimeout(r, 3500));

        if (res.ok) {
            showToast('✅ ' + data.message, 'success');
            e.target.reset();
            loadBooks();
        } else {
            showToast('❌ ' + data.message, 'error');
        }
    } catch {
        showToast('❌ Server unreachable', 'error');
    } finally {
        stopDataFlow(); // Stop animation
    }
});

// ── Members ────────────────────────────────────────────────────────────────────
async function loadMembers() {
    toggleBroom('members', true);
    startDataFlow('fetch'); // Start looping girl toward client

    const tbody = document.querySelector('#members-table tbody');
    tbody.style.opacity = '0';

    try {
        const res = await fetch(`${API}/members`);
        const data = await res.json();

        await new Promise(r => setTimeout(r, 3500));

        renderTable('members-table', data, 3, m =>
            `<tr><td>${m.memberId}</td><td>${m.name}</td><td>${m.email}</td></tr>`
        );
    } catch {
        showToast('❌ Failed to load members', 'error');
    } finally {
        toggleBroom('members', false);
        tbody.style.opacity = '1';
        stopDataFlow(); // Stop animation
    }
}

document.getElementById('form-add-member').addEventListener('submit', async e => {
    e.preventDefault();
    const body = {
        memberId: document.getElementById('member-id').value.trim(),
        name: document.getElementById('member-name').value.trim(),
        email: document.getElementById('member-email').value.trim(),
    };
    startDataFlow('request'); // Start looping girl toward DB
    try {
        const res = await fetch(`${API}/members`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body),
        });
        const data = await res.json();

        // Ensure Gibli girl completes a smooth pass to DB
        await new Promise(r => setTimeout(r, 3500));

        if (res.ok) {
            showToast('✅ ' + data.message, 'success');
            e.target.reset();
            loadMembers();
        } else {
            showToast('❌ ' + data.message, 'error');
        }
    } catch {
        showToast('❌ Server unreachable', 'error');
    } finally {
        stopDataFlow(); // Stop animation
    }
});

// ── Borrow ─────────────────────────────────────────────────────────────────────
document.getElementById('form-borrow').addEventListener('submit', async e => {
    e.preventDefault();
    const body = {
        bookId: document.getElementById('borrow-book-id').value.trim(),
        memberId: document.getElementById('borrow-member-id').value.trim(),
    };
    startDataFlow('request'); // Start looping girl toward DB
    try {
        const res = await fetch(`${API}/borrow`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body),
        });
        const data = await res.json();

        // Ensure Gibli girl completes a smooth pass to DB
        await new Promise(r => setTimeout(r, 3500));

        if (res.ok) {
            showToast('✅ ' + data.message, 'success');
            e.target.reset();
            loadBooks(); // Refresh book status
        } else {
            showToast('❌ ' + data.message, 'error');
        }
    } catch {
        showToast('❌ Server unreachable', 'error');
    } finally {
        stopDataFlow(); // Stop animation
    }
});

// ── Return ─────────────────────────────────────────────────────────────────────
document.getElementById('form-return').addEventListener('submit', async e => {
    e.preventDefault();
    const bookId = document.getElementById('return-book-id').value.trim();
    startDataFlow('request'); // Start looping girl toward DB
    try {
        const res = await fetch(`${API}/return/${encodeURIComponent(bookId)}`, { method: 'POST' });
        const data = await res.json();

        // Ensure Gibli girl completes a smooth pass to DB
        await new Promise(r => setTimeout(r, 3500));

        if (res.ok) {
            showToast('✅ ' + data.message, 'success');
            e.target.reset();
            loadBooks(); // Refresh book status
        } else {
            showToast('❌ ' + data.message, 'error');
        }
    } catch {
        showToast('❌ Server unreachable', 'error');
    } finally {
        stopDataFlow(); // Stop animation
    }
});

// ── Report ─────────────────────────────────────────────────────────────────────
async function loadReport() {
    toggleBroom('report', true);
    startDataFlow('report'); // Start smoother looping girl toward client

    const tbody = document.querySelector('#report-table tbody');
    tbody.style.opacity = '0';

    try {
        const res = await fetch(`${API}/report`);
        const data = await res.json();

        // Match the 3.5s smooth animation
        await new Promise(r => setTimeout(r, 3500));

        renderTable('report-table', data, 4, b =>
            `<tr><td>${b.bookId}</td><td>${b.title}</td><td>${b.author}</td><td>${statusBadge(b.status)}</td></tr>`
        );
    } catch {
        showToast('❌ Failed to generate report', 'error');
    } finally {
        toggleBroom('report', false);
        tbody.style.opacity = '1';
        stopDataFlow(); // Stop animation
    }
}

// ── Digital Clock ─────────────────────────────────────────────────────────────
function updateClock() {
    const clock = document.getElementById('digital-clock');
    if (!clock) return;
    const now = new Date();
    clock.textContent = now.toLocaleTimeString();
}
setInterval(updateClock, 1000);
updateClock();

// ── Initial Load ──────────────────────────────────────────────────────────────
async function init() {
    togglePreloader(true);

    // Safety: ensure preloader dies eventually even if network hangs
    const timeout = setTimeout(() => togglePreloader(false), 5000);

    try {
        await Promise.allSettled([loadBooks(), loadMembers()]);
    } finally {
        clearTimeout(timeout);
        togglePreloader(false);
    }
}
init();
