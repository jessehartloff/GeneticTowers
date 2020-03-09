const tileSize = 30;

const canvas = document.getElementById("canvas");
const context = canvas.getContext("2d");
context.globalCompositeOperation = 'source-over';

function updateView() {
    drawGameBoard(gridSize);

    for (const tower of towers) {
        drawTower(tower.location['x'], tower.location['y']);
    }

    placeCircle(player.location['x'], player.location['y'], '#56bcff', 2.0);

    for (const projectile of projectiles) {
        placeCircle(projectile.location['x'], projectile.location['y'], 'red', 1.0);
    }
}

function drawGameBoard(gridSize) {

    const gridWidth = gridSize['x'];
    const gridHeight = gridSize['y'];

    context.clearRect(0, 0, gridWidth * tileSize, gridHeight * tileSize);

    canvas.setAttribute("width", gridWidth * tileSize);
    canvas.setAttribute("height", gridHeight * tileSize);

    context.strokeStyle = '#bbbbbb';
    for (let j = 0; j <= gridWidth; j++) {
        context.beginPath();
        context.moveTo(j * tileSize, 0);
        context.lineTo(j * tileSize, gridHeight * tileSize);
        context.stroke();
    }
    for (let k = 0; k <= gridHeight; k++) {
        context.beginPath();
        context.moveTo(0, k * tileSize);
        context.lineTo(gridWidth * tileSize, k * tileSize);
        context.stroke();
    }

}

function placeCircle(x, y, color, size) {
    context.fillStyle = color;
    context.beginPath();
    context.arc(x * tileSize,
        y * tileSize,
        size / 10.0 * tileSize,
        0,
        2 * Math.PI);
    context.fill();
    context.strokeStyle = 'black';
    context.stroke();
}

function xComp(degrees) {
    return Math.cos(Math.PI * degrees / 180.0)
}

function yComp(degrees) {
    return Math.sin(Math.PI * degrees / 180.0)
}

function drawTower(x, y) {
    const size = 3.0;

    const scaledSize = size / 10.0 * tileSize;
    const centerX = (x + 0.5) * tileSize;
    const centerY = (y + 0.5) * tileSize;

    context.fillStyle = '#760672';
    context.strokeStyle = 'black';

    context.beginPath();
    context.moveTo(centerX + scaledSize, centerY);
    for (let i = 0; i <= 7; i++) {
        const degrees = i * 60.0;
        context.lineTo(centerX + xComp(degrees) * scaledSize, centerY + yComp(degrees) * scaledSize);
    }

    context.lineWidth = 5;
    context.stroke();
    context.lineWidth = 1;
    context.fill()
}
