import { Link } from 'react-router-dom';

function DestinationCard({ destination }) {
    return (
        <article className="destination-card">
            <Link to={`/destination/${destination.slug}`}>
                <div className="destination-image">
                    <img src={destination.image} alt={destination.name} loading="lazy" />
                    <span className="destination-badge">{destination.type}</span>
                    <span className="destination-rating">⭐ {destination.rating}</span>
                </div>
                <div className="destination-info">
                    <h3>{destination.name}</h3>
                    <p className="destination-location">📍 {destination.location}</p>
                    <div className="destination-details">
                        <span className="destination-price">
                            ₹{Number(destination.price).toLocaleString('en-IN')}
                            <span> / person</span>
                        </span>
                        <span className="destination-duration">🗓️ {destination.duration}</span>
                    </div>
                </div>
            </Link>
        </article>
    );
}

export default DestinationCard;
