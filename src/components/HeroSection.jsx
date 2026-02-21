export default function HeroSection() {
    const handleButtonHover = (e) => {
        const button = e.currentTarget
        const ripple = document.createElement('span')
        ripple.style.position = 'absolute'
        ripple.style.borderRadius = '50%'
        ripple.style.background = 'rgba(255, 255, 255, 0.3)'
        ripple.style.width = '0'
        ripple.style.height = '0'
        ripple.style.transform = 'translate(-50%, -50%)'
        ripple.style.animation = 'ripple 0.6s ease-out'
        button.style.position = 'relative'
        button.style.overflow = 'hidden'
        const rect = button.getBoundingClientRect()
        ripple.style.left = (e.clientX - rect.left) + 'px'
        ripple.style.top = (e.clientY - rect.top) + 'px'
        button.appendChild(ripple)
        setTimeout(() => ripple.remove(), 600)
    }

    const handleClick = (e, targetId) => {
        e.preventDefault()
        const section = document.getElementById(targetId)
        if (section) {
            section.scrollIntoView({ behavior: 'smooth', block: 'start' })
        }
    }

    return (
        <section id="home" className="hero-section">
            <div className="hero-bg-grid"></div>
            <div className="hero-container">
                <div className="hero-content reveal-animate">
                    <p className="hero-greeting">Hello, I'm</p>
                    <h1 className="hero-title">
                        <span className="gradient-text">Nishant Kashyap</span>
                    </h1>
                    <h2 className="hero-subtitle">
                        Full-Stack Developer | Java &amp; Spring Boot • React • Modern UI/UX
                    </h2>
                    <p className="hero-description">
                        Crafting innovative digital experiences with cutting-edge technologies.
                        Transforming ideas into elegant, scalable solutions.
                    </p>
                    <div className="hero-cta-group">
                        <a
                            href="#projects"
                            className="btn btn-primary"
                            onClick={(e) => handleClick(e, 'projects')}
                            onMouseEnter={handleButtonHover}
                        >
                            View My Work
                        </a>
                        <a
                            href="#contact"
                            className="btn btn-secondary"
                            onClick={(e) => handleClick(e, 'contact')}
                            onMouseEnter={handleButtonHover}
                        >
                            Get In Touch
                        </a>
                    </div>
                </div>
                <div className="hero-visual reveal-animate-delay">
                    <div className="cyber-frame">
                        <div className="cyber-corner corner-tl"></div>
                        <div className="cyber-corner corner-tr"></div>
                        <div className="cyber-corner corner-bl"></div>
                        <div className="cyber-corner corner-br"></div>
                        <div className="code-snippet">
                            <div className="code-line">
                                <span className="code-keyword">const</span> developer = {'{'}
                            </div>
                            <div className="code-line">
                                {' '}<span className="code-property">name</span>:{' '}
                                <span className="code-string">'Nishant'</span>,
                            </div>
                            <div className="code-line">
                                {' '}<span className="code-property">role</span>:{' '}
                                <span className="code-string">
                                    'Full-Stack | Java &amp; Spring Boot • React • Modern UI/UX'
                                </span>,
                            </div>
                            <div className="code-line">
                                {' '}<span className="code-property">passion</span>:{' '}
                                <span className="code-string">'Innovation'</span>
                            </div>
                            <div className="code-line">{'};'}</div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="scroll-indicator">
                <div className="scroll-line"></div>
                <span>Scroll to explore</span>
            </div>
        </section>
    )
}
