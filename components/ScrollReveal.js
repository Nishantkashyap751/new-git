'use client';

import { useEffect } from 'react';

export default function ScrollReveal() {
    useEffect(() => {
        // Find all elements with the 'reveal' class
        const observeElements = () => {
            const reveals = document.querySelectorAll('.reveal:not(.active)');
            
            const observer = new IntersectionObserver((entries) => {
                entries.forEach((entry) => {
                    if (entry.isIntersecting) {
                        entry.target.classList.add('active');
                        // Optional: stop observing once revealed to only animate once
                        // observer.unobserve(entry.target);
                    }
                });
            }, {
                root: null,
                rootMargin: '0px',
                threshold: 0.15 // Trigger when 15% of the element is visible
            });

            reveals.forEach((element) => {
                observer.observe(element);
            });

            return () => {
                reveals.forEach(element => observer.unobserve(element));
            };
        };

        // Run the observer
        const cleanup = observeElements();

        // Also re-run if path changes or DOM updates significantly
        // In Next.js App Router, layout doesn't unmount, so we use MutationObserver
        // to catch new elements (like when navigating between pages)
        const mutationObserver = new MutationObserver(() => {
            observeElements();
        });

        mutationObserver.observe(document.body, { childList: true, subtree: true });

        return () => {
            if (cleanup) cleanup();
            mutationObserver.disconnect();
        };
    }, []);

    return null; // This component doesn't render anything
}
