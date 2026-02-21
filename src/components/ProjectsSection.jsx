import { projects } from '../data/projects'
import useScrollReveal from '../hooks/useScrollReveal'
import ProjectCard from './ProjectCard'

export default function ProjectsSection() {
    const headerRef = useScrollReveal()

    return (
        <section id="projects" className="projects-section">
            <div className="section-container">
                <div className="section-header scroll-reveal" ref={headerRef}>
                    <h2 className="section-title">
                        Featured <span className="gradient-text">Projects</span>
                    </h2>
                    <p className="section-subtitle">
                        Showcase of innovative solutions and technical excellence
                    </p>
                </div>
                <div className="projects-grid">
                    {projects.map((project) => (
                        <ProjectCard key={project.id} project={project} />
                    ))}
                </div>
            </div>
        </section>
    )
}
