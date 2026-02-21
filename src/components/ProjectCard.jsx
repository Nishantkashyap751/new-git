import { useEffect, useRef } from 'react'

export default function ProjectCard({ project }) {
    const cardRef = useRef(null)
    const imgRef = useRef(null)

    // Scroll reveal
    useEffect(() => {
        const el = cardRef.current
        if (!el) return
        const observer = new IntersectionObserver(
            (entries) => {
                entries.forEach((entry) => {
                    if (entry.isIntersecting) {
                        entry.target.classList.add('revealed')
                        observer.unobserve(entry.target)
                    }
                })
            },
            { threshold: 0.15, rootMargin: '0px 0px -100px 0px' }
        )
        observer.observe(el)
        return () => observer.disconnect()
    }, [])

    // Generate placeholder image via canvas
    useEffect(() => {
        const img = imgRef.current
        if (!img) return

        const canvas = document.createElement('canvas')
        canvas.width = 600
        canvas.height = 400
        const ctx = canvas.getContext('2d')
        const [primaryColor, secondaryColor] = project.placeholderColors

        // Gradient background
        const gradient = ctx.createLinearGradient(0, 0, canvas.width, canvas.height)
        gradient.addColorStop(0, primaryColor)
        gradient.addColorStop(1, secondaryColor)
        ctx.fillStyle = gradient
        ctx.fillRect(0, 0, canvas.width, canvas.height)

        // Geometric pattern
        ctx.strokeStyle = 'rgba(255, 255, 255, 0.1)'
        ctx.lineWidth = 2
        for (let i = 0; i < 10; i++) {
            ctx.beginPath()
            ctx.arc(
                Math.random() * canvas.width,
                Math.random() * canvas.height,
                Math.random() * 100 + 50,
                0,
                Math.PI * 2
            )
            ctx.stroke()
        }

        // Grid overlay
        ctx.strokeStyle = 'rgba(0, 255, 157, 0.05)'
        ctx.lineWidth = 1
        const gridSize = 50
        for (let x = 0; x < canvas.width; x += gridSize) {
            ctx.beginPath()
            ctx.moveTo(x, 0)
            ctx.lineTo(x, canvas.height)
            ctx.stroke()
        }
        for (let y = 0; y < canvas.height; y += gridSize) {
            ctx.beginPath()
            ctx.moveTo(0, y)
            ctx.lineTo(canvas.width, y)
            ctx.stroke()
        }

        // Glowing accents
        ctx.shadowBlur = 30
        ctx.shadowColor = primaryColor
        ctx.fillStyle = primaryColor
        for (let i = 0; i < 5; i++) {
            ctx.beginPath()
            ctx.arc(
                Math.random() * canvas.width,
                Math.random() * canvas.height,
                Math.random() * 5 + 2,
                0,
                Math.PI * 2
            )
            ctx.fill()
        }

        img.src = canvas.toDataURL()
    }, [project.placeholderColors])

    // Parallax on scroll
    useEffect(() => {
        const handleScroll = () => {
            const card = cardRef.current
            const img = imgRef.current
            if (!card || !img) return
            const rect = card.getBoundingClientRect()
            const scrollPercent =
                (window.innerHeight - rect.top) / (window.innerHeight + rect.height)
            if (scrollPercent > 0 && scrollPercent < 1) {
                const moveAmount = (scrollPercent - 0.5) * 30
                img.style.transform = `scale(1.1) translateY(${moveAmount}px)`
            }
        }
        window.addEventListener('scroll', handleScroll)
        return () => window.removeEventListener('scroll', handleScroll)
    }, [])

    // Tilt effect handlers
    const handleMouseEnter = () => {
        if (cardRef.current) cardRef.current.style.transition = 'none'
    }

    const handleMouseMove = (e) => {
        const card = cardRef.current
        if (!card) return
        const rect = card.getBoundingClientRect()
        const x = e.clientX - rect.left
        const y = e.clientY - rect.top
        const centerX = rect.width / 2
        const centerY = rect.height / 2
        const rotateX = ((y - centerY) / centerY) * -10
        const rotateY = ((x - centerX) / centerX) * 10
        card.style.transform = `perspective(1000px) rotateX(${rotateX}deg) rotateY(${rotateY}deg) translateY(-10px)`
    }

    const handleMouseLeave = () => {
        const card = cardRef.current
        if (!card) return
        card.style.transition = 'all 0.4s cubic-bezier(0.4, 0, 0.2, 1)'
        card.style.transform = 'perspective(1000px) rotateX(0) rotateY(0) translateY(0)'
    }

    return (
        <div
            className="project-card glass scroll-reveal"
            ref={cardRef}
            onMouseEnter={handleMouseEnter}
            onMouseMove={handleMouseMove}
            onMouseLeave={handleMouseLeave}
        >
            <div className="project-image parallax-image">
                <img ref={imgRef} alt={project.imgAlt} id={project.imgId} />
                <div className="project-overlay">
                    <div className="project-tech">
                        {project.techTags.map((tag) => (
                            <span key={tag} className="tech-tag">
                                {tag}
                            </span>
                        ))}
                    </div>
                </div>
            </div>
            {project.title && (
                <div className="project-content">
                    <h3 className="project-title">{project.title}</h3>
                    <p className="project-description">{project.description}</p>
                    <div className="project-links">
                        <a href={project.liveLink} className="project-link" target="_blank" rel="noopener noreferrer">
                            Live Demo →
                        </a>
                        <a href={project.githubLink} className="project-link" target="_blank" rel="noopener noreferrer">
                            GitHub →
                        </a>
                    </div>
                </div>
            )}
        </div>
    )
}
