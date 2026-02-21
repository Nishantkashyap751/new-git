import { useEffect } from 'react'
import ContactSection from './components/ContactSection'
import ExpertiseSection from './components/ExpertiseSection'
import Footer from './components/Footer'
import HeroSection from './components/HeroSection'
import Navbar from './components/Navbar'
import ProjectsSection from './components/ProjectsSection'
import useCursorGlow from './hooks/useCursorGlow'

function App() {
    useCursorGlow()

    useEffect(() => {
        // Smooth page load animation
        document.body.style.opacity = '0'
        document.body.style.transition = 'opacity 0.5s ease'
        setTimeout(() => {
            document.body.style.opacity = '1'
        }, 100)

        // Easter egg: Konami code
        let konamiCode = []
        const konamiSequence = [
            'ArrowUp', 'ArrowUp', 'ArrowDown', 'ArrowDown',
            'ArrowLeft', 'ArrowRight', 'ArrowLeft', 'ArrowRight',
            'b', 'a',
        ]

        const handleKeydown = (e) => {
            konamiCode.push(e.key)
            konamiCode.splice(
                -konamiSequence.length - 1,
                konamiCode.length - konamiSequence.length
            )
            if (konamiCode.join('').includes(konamiSequence.join(''))) {
                const colors = ['--neon-emerald', '--electric-cyan']
                let index = 0
                const interval = setInterval(() => {
                    document.documentElement.style.setProperty(
                        colors[index % 2],
                        index % 2 === 0 ? '#ff00ff' : '#ffff00'
                    )
                    index++
                    if (index > 10) {
                        clearInterval(interval)
                        location.reload()
                    }
                }, 200)
            }
        }

        document.addEventListener('keydown', handleKeydown)

        console.log(
            '%c👨‍💻 Portfolio by Nishant Kashyap',
            'font-size: 20px; font-weight: bold; color: #00ff9d;'
        )
        console.log(
            '%cBuilt with React & passion',
            'font-size: 14px; color: #00d9ff;'
        )

        return () => document.removeEventListener('keydown', handleKeydown)
    }, [])

    return (
        <>
            <Navbar />
            <HeroSection />
            <ProjectsSection />
            <ExpertiseSection />
            <ContactSection />
            <Footer />
        </>
    )
}

export default App
