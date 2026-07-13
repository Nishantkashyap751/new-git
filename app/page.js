import DestinationCard from '../components/DestinationCard';
import NewsletterForm from '../components/NewsletterForm';
import fs from 'fs';
import path from 'path';

export default function Home() {
    // Read destinations from the public folder (Server-side)
    const filePath = path.join(process.cwd(), 'public', 'destinations.json');
    const fileContent = fs.readFileSync(filePath, 'utf8');
    const destinations = JSON.parse(fileContent);
    const featured = destinations.filter(d => d.featured).slice(0, 3);

    return (
        <div className="home-page">
            <header className="hero">
                <video
                    autoPlay
                    loop
                    muted
                    playsInline
                    className="hero-video"
                >
                    <source src="/videos/hero-video.mp4" type="video/mp4" />
                </video>
                <div className="hero-texture-overlay"></div>
                <div className="hero-particles"></div>
                <div className="hero-content">
                    <h1>Discover Your Next<br /><span style={{color: 'var(--primary-color)'}}>Adventure</span></h1>
                    <p>Explore breathtaking destinations around the world with our curated premium travel experiences</p>
                    <a href="/destinations" className="btn btn-primary">Explore Destinations</a>
                </div>
                <div className="scroll-indicator">
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                        <polyline points="6 9 12 15 18 9"></polyline>
                    </svg>
                </div>
            </header>

            <section className="features">
                <div className="container">
                    <div className="section-header reveal">
                        <h2>Why Choose Us</h2>
                        <p>We provide the ultimate premium travel experiences</p>
                    </div>
                    <div className="features-grid">
                        <div className="feature-card reveal delay-1">
                            <div className="feature-icon-wrapper">🌍</div>
                            <h3>Curated Experiences</h3>
                            <p>Hand-picked destinations and expertly crafted itineraries for unforgettable journeys</p>
                        </div>
                        <div className="feature-card reveal delay-2">
                            <div className="feature-icon-wrapper">💎</div>
                            <h3>Premium Value</h3>
                            <p>Luxurious experiences at competitive prices with no hidden costs</p>
                        </div>
                        <div className="feature-card reveal delay-3">
                            <div className="feature-icon-wrapper">🛡️</div>
                            <h3>Travel Safe</h3>
                            <p>24/7 VIP support and comprehensive travel protection for peace of mind</p>
                        </div>
                        <div className="feature-card reveal">
                            <div className="feature-icon-wrapper">⭐</div>
                            <h3>Expert Guides</h3>
                            <p>Local experts who bring destinations to life with insider knowledge</p>
                        </div>
                    </div>
                </div>
            </section>

            <section className="featured-destinations">
                <div className="container">
                    <div className="section-header reveal">
                        <h2>Featured Destinations</h2>
                        <p>Explore our most popular premium travel packages</p>
                    </div>

                    <div className="destinations-grid reveal delay-1">
                        {featured.length === 0 ? (
                            <p className="text-center">No featured destinations available.</p>
                        ) : (
                            featured.map(dest => (
                                <DestinationCard key={dest.id} destination={dest} />
                            ))
                        )}
                    </div>

                    <div className="text-center reveal delay-2">
                        <a href="/destinations" className="btn btn-secondary">View All Destinations</a>
                    </div>
                </div>
            </section>

            <section className="promo-banner reveal">
                <div className="container">
                    <div className="promo-content">
                        <h2>Winter Special Offer</h2>
                        <p>Get up to 20% off on selected beach destinations. Book now and save!</p>
                        <a href="/destinations" className="btn">Claim Offer Now</a>
                    </div>
                </div>
            </section>

            <section className="testimonials">
                <div className="container">
                    <div className="section-header reveal">
                        <h2>Traveler Stories</h2>
                        <p>Real experiences from real adventurers</p>
                    </div>
                    <div className="testimonials-grid">
                        <div className="testimonial-card reveal delay-1">
                            <div className="quote-icon">"</div>
                            <div className="testimonial-rating">★★★★★</div>
                            <p>Our Bali trip was absolutely magical! Every detail was perfectly planned, from the temples to the beaches. Highly recommend!</p>
                            <div className="testimonial-author">
                                <div className="author-avatar" style={{backgroundImage: 'url(https://i.pravatar.cc/100?img=1)', backgroundSize: 'cover'}}></div>
                                <div>
                                    <strong>Sarah M.</strong>
                                    <span>Bali, Indonesia</span>
                                </div>
                            </div>
                        </div>
                        <div className="testimonial-card reveal delay-2">
                            <div className="quote-icon">"</div>
                            <div className="testimonial-rating">★★★★★</div>
                            <p>The Paris package exceeded all expectations. The hotel was charming, and the guided tours were informative and fun.</p>
                            <div className="testimonial-author">
                                <div className="author-avatar" style={{backgroundImage: 'url(https://i.pravatar.cc/100?img=11)', backgroundSize: 'cover'}}></div>
                                <div>
                                    <strong>James K.</strong>
                                    <span>Paris, France</span>
                                </div>
                            </div>
                        </div>
                        <div className="testimonial-card reveal delay-3">
                            <div className="quote-icon">"</div>
                            <div className="testimonial-rating">★★★★★</div>
                            <p>Kenya safari was a once-in-a-lifetime experience. Seeing the Big Five in person was absolutely breathtaking!</p>
                            <div className="testimonial-author">
                                <div className="author-avatar" style={{backgroundImage: 'url(https://i.pravatar.cc/100?img=5)', backgroundSize: 'cover'}}></div>
                                <div>
                                    <strong>Emma L.</strong>
                                    <span>Safari, Kenya</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section className="newsletter reveal">
                <div className="container">
                    <div className="newsletter-content">
                        <h2>Unlock VIP Access</h2>
                        <p>Get exclusive deals and travel inspiration delivered to your inbox</p>
                        <NewsletterForm />
                    </div>
                </div>
            </section>
        </div>
    );
}
