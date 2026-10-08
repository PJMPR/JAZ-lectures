(() => {
  'use strict';

  const slides = [...document.querySelectorAll('.slide')];
  const currentLabel = document.querySelector('#current-slide');
  const totalLabel = document.querySelector('#total-slides');
  const progressBar = document.querySelector('#progress-bar');
  const prevButton = document.querySelector('#prev');
  const nextButton = document.querySelector('#next');
  const dotsContainer = document.querySelector('#slide-dots');
  const toast = document.querySelector('#toast');
  let current = 0;
  let toastTimer;

  totalLabel.textContent = String(slides.length).padStart(2, '0');

  const dots = slides.map((slide, index) => {
    const button = document.createElement('button');
    button.type = 'button';
    button.setAttribute('aria-label', `Slajd ${index + 1}: ${slide.dataset.title}`);
    button.addEventListener('click', () => showSlide(index));
    dotsContainer.append(button);
    return button;
  });

  function indexFromHash() {
    const id = decodeURIComponent(location.hash.slice(1));
    const index = slides.findIndex((slide) => slide.id === id);
    return index >= 0 ? index : 0;
  }

  function showSlide(index, updateHash = true) {
    current = Math.max(0, Math.min(index, slides.length - 1));
    slides.forEach((slide, i) => {
      const active = i === current;
      slide.classList.toggle('is-active', active);
      slide.setAttribute('aria-hidden', String(!active));
    });
    dots.forEach((dot, i) => dot.classList.toggle('is-active', i === current));
    currentLabel.textContent = String(current + 1).padStart(2, '0');
    progressBar.style.width = `${((current + 1) / slides.length) * 100}%`;
    prevButton.disabled = current === 0;
    nextButton.disabled = current === slides.length - 1;
    nextButton.setAttribute('aria-label', current === slides.length - 1 ? 'Koniec wykładu' : 'Następny slajd');
    const deckTitle = document.body.dataset.deckTitle || 'JAZ';
    document.title = `${slides[current].dataset.title} // ${deckTitle}`;
    if (updateHash) history.replaceState(null, '', `#${slides[current].id}`);
  }

  function showToast(message) {
    clearTimeout(toastTimer);
    toast.textContent = message;
    toast.classList.add('show');
    toastTimer = setTimeout(() => toast.classList.remove('show'), 1600);
  }

  prevButton.addEventListener('click', () => showSlide(current - 1));
  nextButton.addEventListener('click', () => showSlide(current + 1));
  addEventListener('hashchange', () => showSlide(indexFromHash(), false));

  addEventListener('keydown', (event) => {
    if (event.target.matches('button, a')) return;
    if (['ArrowRight', 'PageDown', ' '].includes(event.key)) {
      event.preventDefault();
      showSlide(current + 1);
    } else if (['ArrowLeft', 'PageUp'].includes(event.key)) {
      event.preventDefault();
      showSlide(current - 1);
    } else if (event.key === 'Home') {
      event.preventDefault();
      showSlide(0);
    } else if (event.key === 'End') {
      event.preventDefault();
      showSlide(slides.length - 1);
    } else if (event.key.toLowerCase() === 'f') {
      if (!document.fullscreenElement) document.documentElement.requestFullscreen?.();
      else document.exitFullscreen?.();
    }
  });

  document.querySelectorAll('.copy-btn').forEach((button) => {
    button.addEventListener('click', async () => {
      const code = button.closest('.code-panel').querySelector('code').innerText;
      try {
        await navigator.clipboard.writeText(code);
        showToast('Kod skopiowany');
      } catch {
        showToast('Nie udało się skopiować kodu');
      }
    });
  });

  document.querySelectorAll('.reveal-btn').forEach((button) => {
    button.addEventListener('click', () => {
      const answer = document.getElementById(button.dataset.target);
      const isHidden = answer.hidden;
      answer.hidden = !isHidden;
      button.setAttribute('aria-expanded', String(isHidden));
      button.textContent = isHidden ? 'Ukryj rozwiązanie' : 'Pokaż rozwiązanie';
    });
  });

  let touchStartX = null;
  addEventListener('touchstart', (event) => { touchStartX = event.changedTouches[0].clientX; }, { passive: true });
  addEventListener('touchend', (event) => {
    if (touchStartX === null) return;
    const distance = event.changedTouches[0].clientX - touchStartX;
    if (Math.abs(distance) > 70) showSlide(current + (distance < 0 ? 1 : -1));
    touchStartX = null;
  }, { passive: true });

  showSlide(indexFromHash(), false);
})();
