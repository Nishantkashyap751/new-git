// ── Animation Engine ─────────────────────────────────────────────────────────
// Controls the Gibli data-flow girl animation

const ANIM_CONFIG = {
  request: { label: 'Sending request...', color: '#00ff9d', direction: 'to-db' },
  fetch: { label: 'Fetching data...', color: '#00d4ff', direction: 'to-client' },
  report: { label: 'Analyzing data...', color: '#00d4ff', direction: 'to-client' },
  error: { label: 'Action failed!', color: '#ff4d6d', direction: 'to-client' },
};

let flowInterval = null;
let isAnimating = false;

/**
 * Start a looping data flow animation.
 * @param {'request'|'fetch'|'error'} type 
 */
function startDataFlow(type) {
  if (isAnimating) stopDataFlow(); // Clear existing

  const cfg = ANIM_CONFIG[type] || ANIM_CONFIG.error;
  const lottie = document.getElementById('data-lottie');
  const statusText = document.getElementById('anim-status-text');

  isAnimating = true;
  statusText.textContent = cfg.label;
  statusText.style.color = cfg.color;

  // Configuration for "to-db" (Client -> Database) vs "to-client" (Database -> Client)
  // Standardized 300px coordinates (+/-) to perfectly match the 600px layout gap
  const isToDB = cfg.direction === 'to-db';
  const scaleX = isToDB ? -1 : 1;
  const startX = isToDB ? '-300px' : '300px';
  const endX = isToDB ? '300px' : '-300px';

  // Standardized smooth timing for all operations
  const duration = 3500;
  const interval = 4000;

  const runOnce = () => {
    if (!isAnimating) return;

    // 1. Reset to start position (hidden)
    lottie.style.transition = 'none';
    lottie.style.opacity = '0';
    lottie.style.transform = `translateX(${startX}) scaleX(${scaleX})`;

    // Force reflow
    void lottie.getBoundingClientRect();

    // 2. Fade in and start moving
    lottie.style.opacity = '1';
    lottie.style.transition = `transform ${duration / 1000}s ease-in-out, opacity 0.3s ease`;
    lottie.play();

    // Synchronization: Trigger DB interaction
    if (isToDB) {
      // Request: Trigger DB when Gibli girl reaches it (Arrival)
      setTimeout(() => { if (isAnimating) playDBOnce(); }, duration);
    } else {
      // Fetch/Report: Trigger DB immediately (Departure)
      playDBOnce();
    }

    // Small delay to ensure horizontal movement triggers
    setTimeout(() => {
      if (!isAnimating) return;
      lottie.style.transform = `translateX(${endX}) scaleX(${scaleX})`;
    }, 50);

    // 3. Fade out at the end
    setTimeout(() => {
      if (!isAnimating) return;
      lottie.style.opacity = '0';
    }, duration);
  };

  // Run immediately and then loop
  flowInterval = setInterval(runOnce, interval);
  runOnce();
}

/**
 * Plays the database animation for exactly one cycle.
 */
function playDBOnce() {
  const dbLottie = document.getElementById('db-lottie');
  if (!dbLottie) return;

  dbLottie.stop(); // Reset to start
  dbLottie.play(); // Play once (since loop is removed in HTML)
}

/**
 * Stop the animation and hide everything.
 */
function stopDataFlow() {
  isAnimating = false;
  clearInterval(flowInterval);
  flowInterval = null;

  const lottie = document.getElementById('data-lottie');
  const statusText = document.getElementById('anim-status-text');

  lottie.style.opacity = '0';
  lottie.style.transition = 'opacity 0.3s ease';
  statusText.textContent = '';
  lottie.pause();
}
