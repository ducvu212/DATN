import {
  DeviceEventEmitter,
  NativeAppEventEmitter,
  NativeEventEmitter,
  NativeModules,
  Platform,
} from 'react-native';

const { RNBackgroundTimer } = NativeModules;
const Emitter = new NativeEventEmitter(RNBackgroundTimer);

class BackgroundTimer {
  constructor() {
    this.uniqueId = 0;
    this.callbacks = {};
  }

  runBackgroundTimer(callback, delay) {
    if (this.eventEmitter) this.removeEventEmitter()
    this.eventEmitter = Emitter.addListener('backgroundTimer.timeout', (id) => {
      if (this.callbacks[id]) {
        const callbackById = this.callbacks[id];
        const { callback } = callbackById;
        if (!this.callbacks[id].interval) {
          delete this.callbacks[id];
        } else {
          RNBackgroundTimer.setTimeout(id, this.callbacks[id].timeout);
        }
        callback();
      }
    });

    const EventEmitter = Platform.select({
      ios: () => NativeAppEventEmitter,
      android: () => DeviceEventEmitter,
    })();
    this.start(0);
    this.backgroundListener = EventEmitter.addListener('backgroundTimer', () => {
      this.backgroundListener.remove();
      this.backgroundClockMethod(callback, delay);
    });
  }

  start(delay = 0) {
    return RNBackgroundTimer.start(delay);
  }

  stop() {
    return RNBackgroundTimer.stop();
  }

  backgroundClockMethod(callback, delay) {
    this.backgroundTimer = this.setTimeout(() => {
        callback();
        this.backgroundClockMethod(callback, delay);
      },
      delay);
  }

  stopBackgroundTimer() {
    this.stop();
    this.clearTimeout(this.backgroundTimer);
  }

  removeBackgroundTimer() {
    if (this.backgroundListener) this.backgroundListener.remove()
  }

  removeEventEmitter() {
    if (this.backgroundListener) this.backgroundListener.remove()
    Emitter.removeAllListeners('backgroundTimer.timeout')
  }

  setTimeout(callback, timeout) {
    this.uniqueId += 1;
    const timeoutId = this.uniqueId;
    this.callbacks[timeoutId] = {
      callback,
      interval: false,
      timeout,
    };

    RNBackgroundTimer.setTimeout(timeoutId, timeout);
    return timeoutId;
  }

  clearTimeout(timeoutId) {
    if (this.callbacks[timeoutId]) {
      delete this.callbacks[timeoutId];
      // RNBackgroundTimer.clearTimeout(timeoutId);
    }
  }
}

export default new BackgroundTimer();
