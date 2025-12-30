import { useEffect, useState } from 'react';
import DestinationCard from './DestinationCard';

function Home() {
    const [featured, setFeatured] = useState([]);
    const [email, setEmail] = useState('');
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        fetch('/destinations.json')
            .then(res => res.json())
            .then(data => {
                const featuredDests = data.filter(d => d.featured).slice(0, 3);
                setFeatured(featuredDests);
                setLoading(false);
            })
            .catch(error => {
                console.error('Error fetching destinations:', error);
                setLoading(false);
            });
    }, []);

    const handleNewsletterSubmit = (e) => {
        e.preventDefault();
        alert(`Thank you for subscribing with ${email}! You'll receive our latest travel updates.`);
        setEmail('');
    };

    return (
        <div className="home-page">
            <header className="hero">
                <div className="hero-content">
                    <h1>Discover Your Next Adventure</h1>
                    <p>Explore breathtaking destinations around the world with our curated travel experiences</p>
                    <a href="/destinations" className="btn btn-primary">Explore Destinations</a>
                </div>
            </header>

            <section className="features">
                <div className="container">
                    <div className="features-grid">
                        <div className="feature-card">
                            <div className="feature-icon">🌍</div>
                            <h3>Curated Experiences</h3>
                            <p>Hand-picked destinations and expertly crafted itineraries for unforgettable journeys</p>
                        </div>
                        <div className="feature-card">
                            <div className="feature-icon">💎</div>
                            <h3>Best Value</h3>
                            <p>Premium experiences at competitive prices with no hidden costs</p>
                        </div>
                        <div className="feature-card">
                            <div className="feature-icon">🛡️</div>
                            <h3>Travel Safe</h3>
                            <p>24/7 support and comprehensive travel protection for peace of mind</p>
                        </div>
                        <div className="feature-card">
                            <div className="feature-icon">⭐</div>
                            <h3>Expert Guides</h3>
                            <p>Local experts who bring destinations to life with insider knowledge</p>
                        </div>
                    </div>
                </div>
            </section>

            <section className="featured-destinations">
                <div className="container">
                    <div className="section-header">
                        <h2>Featured Destinations</h2>
                        <p>Explore our most popular travel packages</p>
                    </div>

                    <div className="destinations-grid">
                        {loading ? (
                            <p className="text-center">Loading destinations...</p>
                        ) : featured.length === 0 ? (
                            <p className="text-center">No featured destinations available.</p>
                        ) : (
                            featured.map(dest => (
                                <DestinationCard key={dest.id} destination={dest} />
                            ))
                        )}
                    </div>

                    <div className="text-center">
                        <a href="/destinations" className="btn btn-secondary">View All Destinations</a>
                    </div>
                </div>
            </section>

            <section className="promo-banner">
                <div className="container">
                    <div className="promo-content">
                        <h2>Winter Special Offer</h2>
                        <p>Get up to 20% off on selected beach destinations. Book now and save!</p>
                        <a href="/destinations" className="btn btn-primary">Book Now</a>
                    </div>
                </div>
            </section>

            <section className="testimonials">
                <div className="container">
                    <div className="section-header">
                        <h2>What Our Travelers Say</h2>
                        <p>Real experiences from real adventurers</p>
                    </div>
                    <div className="testimonials-grid">
                        <div className="testimonial-card">
                            <div className="testimonial-rating">★★★★★</div>
                            <p>"Our Bali trip was absolutely magical! Every detail was perfectly planned, from the temples to the beaches. Highly recommend!"</p>
                            <div className="testimonial-author">
                                <strong>Sarah M.</strong>
                                <span>Bali, Indonesia</span>
                            </div>
                        </div>
                        <div className="testimonial-card">
                            <div className="testimonial-rating">★★★★★</div>
                            <p>"The Paris package exceeded all expectations. The hotel was charming, and the guided tours were informative and fun."</p>
                            <div className="testimonial-author">
                                <strong>James K.</strong>
                                <span>Paris, France</span>
                            </div>
                        </div>
                        <div className="testimonial-card">
                            <div className="testimonial-rating">★★★★★</div>
                            <p>"Kenya safari was a once-in-a-lifetime experience. Seeing the Big Five in person was absolutely breathtaking!"</p>
                            <div className="testimonial-author">
                                <strong>Emma L.</strong>
                                <span>Safari, Kenya</span>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section className="newsletter">
                <div className="container">
                    <div className="newsletter-content">
                        <h2>Subscribe to Our Newsletter</h2>
                        <p>Get exclusive deals and travel inspiration delivered to your inbox</p>

                        <form className="newsletter-form" onSubmit={handleNewsletterSubmit}>
                            <input
                                type="email"
                                placeholder="Enter your email address"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                required
                            />
                            <button type="submit" className="btn btn-primary">Subscribe</button>
                        </form>
                    </div>
                </div>
            </section>
        </div>
    );
}

export default Home;
