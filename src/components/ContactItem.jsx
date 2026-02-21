export default function ContactItem({ contact }) {
    return (
        <div className="contact-item glass">
            <div className="contact-icon">
                <lottie-player
                    src={contact.lottieUrl}
                    background="transparent"
                    speed={contact.lottieSpeed}
                    style={{ width: '180px', height: '180px' }}
                    loop
                    autoplay
                ></lottie-player>
            </div>
            <h3>{contact.title}</h3>
            <a
                href={contact.linkUrl}
                {...(contact.external ? { target: '_blank', rel: 'noopener noreferrer' } : {})}
            >
                {contact.linkText}
            </a>
        </div>
    )
}
