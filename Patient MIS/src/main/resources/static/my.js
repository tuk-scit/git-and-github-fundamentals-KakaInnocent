const currentLocation = location.href
const menuItem = document.querySelectorAll('ul')[0];
currentLocation.addEventListener('click', () => {
	menuItem.classList.toggle('active')
	})