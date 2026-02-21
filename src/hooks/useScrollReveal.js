import { useEffect, useRef } from 'react'

export default function useScrollReveal(options = {}) {
    const ref = useRef(null)

    useEffect(() => {
        const element = ref.current
        if (!element) return

        const observer = new IntersectionObserver(
            (entries) => {
                entries.forEach((entry) => {
                    if (entry.isIntersecting) {
                        entry.target.classList.add('revealed')
                        observer.unobserve(entry.target)
                    }
                })
            },
            {
                threshold: options.threshold || 0.15,
                rootMargin: options.rootMargin || '0px 0px -100px 0px',
            }
        )

        observer.observe(element)
        return () => observer.disconnect()
    }, [])

    return ref
}
