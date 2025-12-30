// Polyfill for global variable
// This is needed for some libraries that expect a global variable to be present
// src/polyfills.ts

import { } from 'zone.js';  // Included with Angular CLI.
(window as any).global = window;