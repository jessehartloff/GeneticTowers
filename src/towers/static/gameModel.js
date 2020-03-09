const gridSize = {"x": 30, "y": 14};

let player = {"location": {"x": 5, "y": 7}, "velocity": {"x": 0, "y": 0}};
const playerSpeed = 3.0;

const towers = [{"location": {"x": 15, "y": 7}}];
let projectiles = [];

let lastUpdateTime = new Date().getTime();

function update(dt) {
    updateLocation(player, dt);
    for (const projectile of projectiles) {
        updateLocation(projectile, dt);
    }
    projectiles = projectiles.filter(function(projectile){
        return projectile.location.x > 0.0 && projectile.location.x <gridSize.x &&
            projectile.location.y >0.0 && projectile.location.y < gridSize.y
    });
    updateView();
}

function updateLocation(obj, dt) {
    obj.location.x += obj.velocity.x * dt;
    obj.location.y += obj.velocity.y * dt;
}


function updatePlayerVelocity() {
    player.velocity.x = 0.0;
    player.velocity.y = 0.0;

    if (keyStates["a"] && !keyStates["d"]) {
        player.velocity.x = -playerSpeed;
    } else if (!keyStates["a"] && keyStates["d"]) {
        player.velocity.x = playerSpeed;
    }

    if (keyStates["w"] && !keyStates["s"]) {
        player.velocity.y = -playerSpeed
    } else if (!keyStates["w"] && keyStates["s"]) {
        player.velocity.y = playerSpeed
    }
}


function fireTowers() {
    towers.forEach(function (tower) {
        const towerCenter = {"x": tower.location.x+0.5, "y": tower.location.y+0.5};
        socket.emit("fire", JSON.stringify({"towerLocation": towerCenter, "player": player}))
    })
}

function addProjectile(event) {
    const projectile = JSON.parse(event);
    projectiles.push(projectile)
}


function startGame() {
    const fps = 30;

    setInterval(function () {
        const currentTime = new Date().getTime();
        update((currentTime - lastUpdateTime) / 1000.0);
        lastUpdateTime = currentTime;
    }, 1000 / fps);

    setInterval(fireTowers, 1000);
}