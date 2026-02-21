import { useEffect } from 'react'

export default function useCursorGlow() {
    useEffect(() => {
        const cursor = document.createElement('div')
        cursor.className = 'cursor-glow'
        document.body.appendChild(cursor)

        const style = document.createElement('style')
        style.textContent = `
      .cursor-glow {
        position: fixed;
        width: 400px;
        height: 400px;
        border-radius: 50%;
        background: radial-gradient(circle, rgba(0, 255, 157, 0.08) 0%, transparent 70%);
        pointer-events: none;
        z-index: 9999;
        transform: translate(-50%, -50%);
        transition: opacity 0.3s ease;
        opacity: 0;
      }
      body:hover .cursor-glow {
        opacity: 1;
      }
    `
        document.head.appendChild(style)

        const handleMouseMove = (e) => {
            cursor.style.left = e.clientX + 'px'
            cursor.style.top = e.clientY + 'px'
        }

        document.addEventListener('mousemove', handleMouseMove)

        return () => {
            document.removeEventListener('mousemove', handleMouseMove)
            cursor.remove()
            style.remove()
        }
    }, [])
}
