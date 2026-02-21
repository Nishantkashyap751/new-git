import { useEffect, useMemo, useRef } from 'react'
import useActiveSection from '../hooks/useActiveSection'

export default function Navbar() {
    const navRef = useRef(null)
    const sectionIds = useMemo(() => ['home', 'projects', 'expertise', 'contact'], [])
    const activeSection = useActiveSection(sectionIds)

    useEffect(() => {
        const handleScroll = () => {
            if (navRef.current) {
                if (window.scrollY > 50) {
                    navRef.current.style.boxShadow = '0 10px 40px rgba(0, 0, 0, 0.5)'
                } else {
                    navRef.current.style.boxShadow = '0 8px 32px rgba(0, 0, 0, 0.37)'
                }
            }
        }
        window.addEventListener('scroll', handleScroll)
        return () => window.removeEventListener('scroll', handleScroll)
    }, [])

    const handleNavClick = (e, targetId) => {
        e.preventDefault()
        const section = document.getElementById(targetId)
        if (section) {
            section.scrollIntoView({ behavior: 'smooth', block: 'start' })
        }
    }

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

    return (
        <nav className="nav-floating" id="mainNav" ref={navRef}>
            <div className="nav-container">
                <a href="#home" className="nav-logo" onClick={(e) => handleNavClick(e, 'home')}>
                    NK<span className="glow-dot">.</span>
                </a>
                <div className="nav-links">
                    {sectionIds.map((id) => (
                        <a
                            key={id}
                            href={`#${id}`}
                            className={`nav-link${activeSection === id ? ' active' : ''}`}
                            onClick={(e) => handleNavClick(e, id)}
                        >
                            {id.charAt(0).toUpperCase() + id.slice(1)}
                        </a>
                    ))}
                </div>
                <a
                    href="#contact"
                    className="nav-cta"
                    onClick={(e) => handleNavClick(e, 'contact')}
                    onMouseEnter={handleButtonHover}
                >
                    Let's Connect
                </a>
            </div>
        </nav>
    )
}
