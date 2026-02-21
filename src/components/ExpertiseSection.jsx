import { useEffect, useRef, useState } from 'react'
import { skillCategories } from '../data/skills'
import useScrollReveal from '../hooks/useScrollReveal'
import SkillBar from './SkillBar'

export default function ExpertiseSection() {
    const [animateSkills, setAnimateSkills] = useState(false)
    const sectionRef = useRef(null)
    const headerRef = useScrollReveal()

    // Observe section to trigger skill bar animations
    useEffect(() => {
        const el = sectionRef.current
        if (!el) return

        const observer = new IntersectionObserver(
            (entries) => {
                if (entries[0].isIntersecting) {
                    setTimeout(() => setAnimateSkills(true), 100)
                    observer.disconnect()
                }
            },
            { threshold: 0.3 }
        )

        observer.observe(el)
        return () => observer.disconnect()
    }, [])

    return (
        <section id="expertise" className="expertise-section" ref={sectionRef}>
            <div className="section-container">
                <div className="section-header scroll-reveal" ref={headerRef}>
                    <h2 className="section-title">
                        Technical <span className="gradient-text">Expertise</span>
                    </h2>
                    <p className="section-subtitle">
                        Mastery across the full development stack
                    </p>
                </div>
                <div className="expertise-grid">
                    {skillCategories.map((category) => (
                        <ExpertiseCategory
                            key={category.title}
                            category={category}
                            animate={animateSkills}
                        />
                    ))}
                </div>
            </div>
        </section>
    )
}

function ExpertiseCategory({ category, animate }) {
    const categoryRef = useRef(null)

    // Scroll reveal for each category card
    useEffect(() => {
        const el = categoryRef.current
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

    return (
        <div className="expertise-category scroll-reveal" ref={categoryRef}>
            <h3 className="expertise-category-title">{category.title}</h3>
            {category.skills.map((skill) => (
                <SkillBar
                    key={skill.name}
                    name={skill.name}
                    percentage={skill.percentage}
                    animate={animate}
                />
            ))}
        </div>
    )
}
