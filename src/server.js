const http = require('http');
const fs = require('fs');
const path = require('path');

const PORT = 5000;
const DATA_FILE = path.join(__dirname, 'inquiries.json');

if (!fs.existsSync(DATA_FILE)) {
    fs.writeFileSync(DATA_FILE, '[]');
}

const server = http.createServer((req, res) => {
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Methods', 'POST, GET, OPTIONS');
    res.setHeader('Access-Control-Allow-Headers', 'Content-Type');

    if (req.method === 'OPTIONS') {
        res.writeHead(204);
        res.end();
        return;
    }

    // 1. GET 
    if (req.url === '/api/inquiries' && req.method === 'GET') {
        fs.readFile(DATA_FILE, 'utf8', (err, data) => {
            if (err) {
                res.writeHead(500, { 'Content-Type': 'application/json' });
                res.end(JSON.stringify({ error: 'Could not read data' }));
            } else {
                res.writeHead(200, { 'Content-Type': 'application/json' });
                res.end(data);
            }
        });
    }
    // 2. POST 
    else if (req.url === '/api/inquiries' && req.method === 'POST') {
        let body = '';

        req.on('data', chunk => {
            body += chunk.toString();
        });

        req.on('end', () => {
            try {
                const newInquiry = JSON.parse(body);

                fs.readFile(DATA_FILE, 'utf8', (err, data) => {
                    let inquiries = [];
                    if (!err && data) {
                        try {
                            inquiries = JSON.parse(data);
                        } catch (e) {
                            inquiries = [];
                        }
                    }

                    inquiries.push(newInquiry);

                    fs.writeFile(DATA_FILE, JSON.stringify(inquiries, null, 2), (writeErr) => {
                        if (writeErr) {
                            res.writeHead(500, { 'Content-Type': 'application/json' });
                            res.end(JSON.stringify({ error: 'Could not save data' }));
                        } else {
                            res.writeHead(201, { 'Content-Type': 'application/json' });
                            res.end(JSON.stringify({ message: 'Inquiry saved successfully', savedData: newInquiry }));
                        }
                    });
                });
            } catch (parseErr) {
                res.writeHead(400, { 'Content-Type': 'application/json' });
                res.end(JSON.stringify({ error: 'Invalid JSON data' }));
            }
        });
    }
    else {
        res.writeHead(404, { 'Content-Type': 'application/json' });
        res.end(JSON.stringify({ error: 'Not Found' }));
    }
});

server.listen(PORT, () => {
    console.log(`Server is running at http://localhost:${PORT}`);
});
