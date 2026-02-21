import { useEffect, useState } from 'react'

export default function useActiveSection(sectionIds) {
    const [activeSection, setActiveSection] = useState(sectionIds[0] || '')

    useEffect(() => {
        const handleScroll = () => {
            let current = ''
            sectionIds.forEach((id) => {
                const section = document.getElementById(id)
                if (section) {
                    const sectionTop = section.offsetTop
                    if (window.pageYOffset >= sectionTop - 200) {
                        current = id
                    }
                }
            })
            if (current) setActiveSection(current)
        }

        window.addEventListener('scroll', handleScroll)
        return () => window.removeEventListener('scroll', handleScroll)
    }, [sectionIds])

    return activeSection
}
