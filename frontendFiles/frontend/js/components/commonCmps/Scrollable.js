
class Scrollable {

    constructor (config) {
        this.min = config.min;
        this.max = config.max;
        this.isTransition = config.isTransition;
        this.cssProperty = config.cssProperty;
        this.currentValue = config.currentValue;
        this.parentLength = config.parentLength;
        this.onMax = config.onMax;
        this.unMax = config.unMax;
        this.reachMax = false;
        this.onMin = config.onMin;
        this.unMin = config.unMin;
        this.reachMin = true;

        this.backToMax = config.backToMax;
        this.backToMin = config.backToMin;

        this.transitionDelay = config.transitionDalay || Scrollable.defaultScroll.transitionDelay;
        this.velocity = config.velocity || Scrollable.defaultScroll.velocity;
    }

    scrollTop (domElement, scroll, withAnimation) {

        this.reachMax = false;

        if (withAnimation) {
            domElement.style.transition = "all " + this.transitionDelay + "s";

            setTimeout(function () {
                domElement.style.transition = "none";
            }, this.transitionDelay * 1000);
        }

        if (!scroll && scroll !== 0) {
            scroll = this.velocity;
        }

        this.currentValue = Math.min(scroll + this.currentValue, this.min);
        console.log("scrollTop");
        if (this.currentValue === this.min && this.reachMin && this.backToMax && withAnimation) {
            this.reachMin = false;
            this.scrollBottom(domElement, this.max, true);

            return;
        }

        domElement.style[this.cssProperty] = this.currentValue + "px";

        if (this.min === this.currentValue) {
            this.onMin();
            this.reachMin = true;
        } else {
            if (this.reachMin) {
                this.unMin();
            }
        }

        if (this.reachMax) {
            this.unMax();
        }
    }


    scrollBottom (domElement, scroll, withAnimation) {

        this.reachMin = false;

        if (withAnimation) {
            domElement.style.transition = "all " + this.transitionDelay + "s";

            setTimeout(function () {
                domElement.style.transition = "none";
            }, this.transitionDelay * 1000);
        }

        if (!scroll && scroll !== 0) {
            scroll = this.velocity;
        }

        this.currentValue = Math.max(this.currentValue - scroll, -this.max + this.parentLength - 25);
        console.log("scrollBottom");
        if (this.reachMax && this.backToMin && withAnimation) {
            this.reachMax = false;
            this.scrollTop(domElement, this.max, true);

            return;
        }

        domElement.style[this.cssProperty] = this.currentValue + "px";

        if (-this.max + this.parentLength - 25 === this.currentValue) {
            this.onMax();
            this.reachMax = true;
        } else {
            if (this.reachMax) {
                this.unMax();
                this.reachMax = false;
            }
        }

        if (this.reachMin) {
            this.unMin();
        }
    }

    getCurrentValue () {
        return this.currentValue;
    }

}

Scrollable.defaultScroll = {
    velocity: 300,
    transitionDelay: 0.5
};
