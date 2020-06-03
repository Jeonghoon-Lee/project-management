var navItems = document.querySelectorAll('nav .nav-item');

if (document.location.href.match('employees')) {
    navItems[1].classList.add('active');
} else if (document.location.href.match('projects')) {
    navItems[2].classList.add('active');
} else {
    navItems[0].classList.add('active');
}
