const WebpageServer = require('./WebpageServer')
const TCPServer = require('./TCPServer')
const SocketIOServer = require('./SocketIOServer')

const portNumber = readPortNumberFromArguments()

const webpageCallbacks = {
    onWebpageReady: () => server.send( { name: 'webpage-ready' } ),
    onSonarActivated: object => server.send( { name: 'sonarActivated', arg: object } ),
    onCollision: objectName => server.send( { name: 'collision', arg: objectName } )
}

const webpageServer = new WebpageServer(webpageCallbacks)
const server = new TCPServer( portNumber, command => webpageServer[command.name](command.arg) )
// const server = new SocketIOServer( portNumber, command => command => webpageServer[command.name](command.arg) )

function readPortNumberFromArguments() {
    const port = Number(process.argv[2])
    if(!port || port < 0 || port >= 65536) {
        console.error("This script expects a valid port number (>= 0 and < 65536) as argument.")
        process.exit()
    }

    return port
}
