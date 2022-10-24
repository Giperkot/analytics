/**
 * Created by Odohar on 30.08.2018.
 */

class Point2D {

    constructor(x, y) {
        this.x = x;
        this.y = y;
    }

    getDistance(point) {
        return Math.hypot(point.x - this.x, point.y - this.y);
    }
}
