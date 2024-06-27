document.addEventListener('DOMContentLoaded', function () {
    const carousel = document.querySelector('.carousel');
    const items = document.querySelectorAll('.carousel-item');
    const nextButton = document.querySelector('.carousel-button.next');
    const prevButton = document.querySelector('.carousel-button.prev');
    const previews = document.querySelectorAll('.carousel-preview-item img');

    let currentIndex = 0;
    let autoPlayInterval;

    function updateCarousel() {
        const offset = -currentIndex * 100;
        carousel.style.transform = `translateX(${offset}%)`;
        previews.forEach((preview, index) => {
            preview.classList.remove('active');
            if (index === currentIndex + 1 || (currentIndex === items.length - 1 && index === 0)) {
                preview.classList.add('active');
            }
        });
    }

    function nextSlide() {
        currentIndex = (currentIndex + 1) % items.length;
        updateCarousel();
    }

    function prevSlide() {
        currentIndex = (currentIndex - 1 + items.length) % items.length;
        updateCarousel();
    }

    nextButton.addEventListener('click', () => {
        nextSlide();
        resetAutoPlay();
    });

    prevButton.addEventListener('click', () => {
        prevSlide();
        resetAutoPlay();
    });

    function startAutoPlay() {
        autoPlayInterval = setInterval(nextSlide, 3000); // Avanza ogni 4 secondi
    }

    function stopAutoPlay() {
        clearInterval(autoPlayInterval);
    }

    function resetAutoPlay() {
        stopAutoPlay();
        startAutoPlay();
    }

    // Initialize carousel and start autoplay
    updateCarousel();
    startAutoPlay();
});
