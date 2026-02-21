import { contacts } from '../data/contacts'
import useScrollReveal from '../hooks/useScrollReveal'
import ContactItem from './ContactItem'

export default function ContactSection() {
    const headerRef = useScrollReveal()
    const contentRef = useScrollReveal()

    return (
        <section id="contact" className="contact-section">
            <div className="section-container">
                <div className="section-header scroll-reveal" ref={headerRef}>
                    <h2 className="section-title">
                        Let's <span className="gradient-text">Connect</span>
                    </h2>
                    <p className="section-subtitle">
                        Ready to bring your next project to life
                    </p>
                </div>
                <div className="contact-content scroll-reveal" ref={contentRef}>
                    <div className="contact-info">
                        {contacts.map((contact) => (
                            <ContactItem key={contact.title} contact={contact} />
                        ))}
                    </div>
                </div>
            </div>
        </section>
    )
}
