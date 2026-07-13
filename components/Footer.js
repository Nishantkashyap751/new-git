'use client';

import Link from 'next/link';

export default function Footer() {
    return (
        <footer className="footer reveal">
            <div className="container">
                <div className="footer-grid">
                    <div className="footer-section footer-logo-section reveal delay-1">
                        <img src="/images/logo.png" alt="Elite Adventures" className="footer-logo" />
                        <p>Creating unforgettable premium travel experiences since 2010. Your adventure starts here.</p>
                    </div>
                    <div className="footer-section reveal delay-2">
                        <h4>Quick Links</h4>
                        <ul>
                            <li><Link href="/">Home</Link></li>
                            <li><Link href="/destinations">Destinations</Link></li>
                            <li><Link href="/contact">Contact</Link></li>
                        </ul>
                    </div>
                    <div className="footer-section reveal delay-3">
                        <h4>Contact Info</h4>
                        <ul>
                            <li>Elite Towers, New York</li>
                            <li>+1 (800) 123-4567</li>
                            <li>vip@eliteadventures.com</li>
                        </ul>
                    </div>
                    <div className="footer-section reveal delay-3">
                        <h4>Follow Us</h4>
                        <div className="social-links">
                            <a href="#" target="_blank" rel="noreferrer" aria-label="Facebook">
                                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="M18 2h-3a5 5 0 0 0-5 5v3H7v4h3v8h4v-8h3l1-4h-4V7a1 1 0 0 1 1-1h3z"></path></svg>
                            </a>
                            <a href="#" target="_blank" rel="noreferrer" aria-label="Instagram">
                                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><rect x="2" y="2" width="20" height="20" rx="5" ry="5"></rect><path d="M16 11.37A4 4 0 1 1 12.63 8 4 4 0 0 1 16 11.37z"></path><line x1="17.5" y1="6.5" x2="17.51" y2="6.5"></line></svg>
                            </a>
                            <a href="#" target="_blank" rel="noreferrer" aria-label="Twitter">
                                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="M22 4s-.7 2.1-2 3.4c1.6 10-9.4 17.3-18 11.6 2.2.1 4.4-.6 6-2C3 15.5.5 9.6 3 5c2.2 2.6 5.6 4.1 9 4-.9-4.2 4-6.6 7-3.8 1.1 0 3-1.2 3-1.2z"></path></svg>
                            </a>
                        </div>
                    </div>
                </div>
                <div className="footer-bottom">
                    <p>&copy; 2024 Elite Adventures. All rights reserved.</p>
                </div>
            </div>
        </footer>
    );
}
