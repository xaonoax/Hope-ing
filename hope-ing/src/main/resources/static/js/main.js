/*
	Hope-ing Main
	Artist : A-on
 */

document.addEventListener('DOMContentLoaded', function() {
	const bannerSlider = document.querySelector('.banner_slider');
	const banners = bannerSlider.querySelectorAll('.banner');
	const prevButton = document.querySelector('.prev_button');
	const nextButton = document.querySelector('.next_button');
	const indicator = document.querySelector('.indicator');
	
	let currentIndex = 0;
	
	function showBanner(index) {
		bannerSlider.style.transform = `translateX(-${index * 100}%)`;
		
		// 배너 인덱스 표시 업데이트
		const indicatorSpans = indicator.querySelectorAll('span');
		indicatorSpans.forEach((span, i) => {
			span.classList.toggle('active', i === index);
		});
	}
	
	function prevSlide() {
		currentIndex = (currentIndex === 0) ? banners.length - 1 : currentIndex - 1;
		showBanner(currentIndex);
	}
	
	function nextSlide() {
		currentIndex = (currentIndex === banners.length - 1) ? 0 : currentIndex + 1;
		showBanner(currentIndex);
	}
	
	prevButton.addEventListener('click', prevSlide);
	nextButton.addEventListener('click', nextSlide);
	
	// 배너 자동 넘김 설정
	const autoSlideInterval = setInterval(nextSlide, 3000);
	
	// 배너 인덱스 표시 생성
	for (let i = 0; i < banners.length; i++) {
		const span = document.createElement('span');
		span.addEventListener('click', () => {
			showBanner(i);
			currentIndex = i;
		});
		indicator.appendChild(span);
	}
	// 초기 배너 표시
	showBanner(currentIndex);
});