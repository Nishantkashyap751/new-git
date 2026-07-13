import DestinationsList from '../../components/DestinationsList';
import fs from 'fs';
import path from 'path';

export default function DestinationsPage() {
    const filePath = path.join(process.cwd(), 'public', 'destinations.json');
    const fileContent = fs.readFileSync(filePath, 'utf8');
    const destinations = JSON.parse(fileContent);

    return (
        <div className="destinations-page">
            <div className="page-header reveal">
                <div className="container">
                    <h1>Explore Destinations</h1>
                    <p>Find your perfect premium adventure from our curated collection of amazing destinations</p>
                </div>
            </div>
            <DestinationsList initialDestinations={destinations} />
        </div>
    );
}
