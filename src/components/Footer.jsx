export default function Footer() {
    const handleClick = (e, targetId) => {
        e.preventDefault()
        const section = document.getElementById(targetId)
        if (section) {
            section.scrollIntoView({ behavior: 'smooth', block: 'start' })
        }
    }

    return (
        <footer className="footer">
            <div className="footer-container">
                <p>© 2026 Nishant Kashyap. Crafted with precision and passion.</p>
                <div className="footer-links">
                    <a href="#home" onClick={(e) => handleClick(e, 'home')}>Home</a>
                    <a href="#projects" onClick={(e) => handleClick(e, 'projects')}>Projects</a>
                    <a href="#expertise" onClick={(e) => handleClick(e, 'expertise')}>Expertise</a>
                    <a href="#contact" onClick={(e) => handleClick(e, 'contact')}>Contact</a>
                </div>
            </div>
        </footer>
    )
}
