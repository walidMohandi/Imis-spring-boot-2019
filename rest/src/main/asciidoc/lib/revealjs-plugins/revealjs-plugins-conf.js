keyboard: { // Reserved : N SPACE P H L K J Left Right Up Down Home End, B . Pause F ESC O S
	// List of codes here : https://keycode.info/
	// TODO : Use API to show shortcuts in '?' : Reveal.registerKeyboardShortcut('V', 'View slide fragments');
	34: function() { let {h, v} = Reveal.getIndices(); Reveal.slide(h, v, +Infinity); }, // 'PageDown' show all fragments
	33: function() { let {h, v} = Reveal.getIndices(); Reveal.slide(h, v, -1); }, // 'PageUp' show no fragment
	73: function() { window.open("../index.html","_self") }, // 'i' to index page
	76: function() { RevealSpotlight.toggleSpotlight() }, // Spotlight : alternative to toggleSpotlightOnMouseDown toggle spotlight by pressing key 'c'
	// 80: function() { RevealSpotlight.togglePresentationMode(); }, // Spotlight : 'p' enter/leave presentation mode
	78: function() { RevealChalkboard.toggleNotesCanvas() }, // toggle notes canvas when 'n' is pressed
	67: function() { RevealChalkboard.toggleChalkboard() },	// toggle chalkboard when 'c' is pressed
	46: function() { RevealChalkboard.clear() }, // clear chalkboard when 'DEL' is pressed
	 8: function() { RevealChalkboard.reset() },	// reset chalkboard data on current slide when 'BACKSPACE' is pressed
	68: function() { RevealChalkboard.download() },	// download recorded chalkboard drawing when 'd' is pressed
}, 
//
// CHALKBOARD PLUGIN https://github.com/rajgoel/reveal.js-plugins/tree/master/chalkboard
//
chalkboard: { 
	readOnly: false, // Configuation option allowing to prevent changes to existing drawings.
	transition: 800, // Gives the duration (in milliseconds) of the transition for a slide change, so that the notes canvas is drawn after the transition is completed.
	theme: "chalkboard", // Can be set to either "chalkboard" or "whiteboard".
	color: [ 'rgba(150,150,150,1)', 'rgba(255,255,255,0.5)' ], // The first value gives the pen color, the second value gives the color of the chalk.
	background: [ 'rgba(141,191,68,.1)', 'revealjs-plugins/chalkboard/img/blackboard.png' ] // The first value expects a (semi-)transparent color which is used to provide visual feedback that the notes canvas is enabled, the second value expects a filename to a background image for the chalkboard.
},
//
// NOTES POINTER PLUGIN https://github.com/dougalsutherland/reveal.js-notes-pointer
//
notes_pointer: {
	pointer: {
		size: 15,  // in pixels (scaled like the rest of reveal.js)
		color: 'rgba(239,82,91,0.8)',  // something valid for css background-color
		key: 'P' // '.' does not work
	},
	notes: {
		key: 'S'
	}
},
//
// SPOTLIGHT PLUGIN https://github.com/denniskniep/reveal.js-plugin-spotlight
//
spotlight: {
	size: 120, // size of the spotlight
	lockPointerInsideCanvas: false, // true: Locks the mouse pointer inside the presentation
	toggleSpotlightOnMouseDown: false, // toggle spotlight by holding down the mouse key
	presentingCursor: 'none', // choose the cursor when spotlight is on. Maybe "crosshair"?. Does not draw cursor in presentation mode
	useAsPointer: false, // enable pointer mode
},
//
// MENU PLUGIN https://github.com/denehyg/reveal.js-menu
//
menu: {
	numbers: true, // Add slide numbers to the titles in the slide list.
	titleSelector: 'h1, h2, div.title, caption.title, #toctitle', // Specifies which slide elements will be used for generating the slide titles in the menu. 
	transitions: true, // Specifies if the transitions menu panel will be shown.
	openButton: true, // Adds a menu button to the slides to open the menu panel.
	openSlideNumber: false, // If 'true' allows the slide number in the presentation to open the menu panel
	loadIcons: true, // By default the menu will load it's own font-awesome library icons
	sticky: true, // If 'true', the sticky option will leave the menu open until it is explicitly closed
	custom: [ { title: 'Keys', icon: '<i class="fa fa-keyboard">', content: `
		<ul class="slide-menu-items">
		<li class="slide-menu-item">
			<h3>Core</h3>
			<p>? : Show core keys</p>
		</li>
		<li class="slide-menu-item">
			<h3>Zoom</h3>
			<p>ALT+CLICK : Zoom-in</p>
		</li>
		<li class="slide-menu-item">
			<h3>Notes Pointer / Spotlight</h3>
			<p>P : Toggle pointer on/off</p>
			<p>L : Toggle spotlight on/off</p>
		</li>
		<li class="slide-menu-item">
			<h3>Skip fragments</h3>
			<p>PageDown : Show all fragments</p>
			<p>PageUp : Show no fragment</p>
		</li>
		<li class="slide-menu-item">
			<h3>Chalkboard</h3>
			<p>N : Notes canvas on/off</p>
			<p>C : Chalkboard on/off</p>
			<p>DEL : Clear canvas/chalkboard</p>
			<p>BASCKSPACE : Reset chalkboard data on current slide</p>
			<p>D : Download drawing as JSON</p>
		</li>
		<li class="slide-menu-item">
			<h3>Menu</h3>
			<p>M : Open menu</p>
			<p>H or LEFT : Next left panel</p>
			<p>L or RIGHT : Next right panel</p>
			<p>K or UP : Up</p>
			<p>J or DOWN : Down</p>
			<p>U or PAGE UP : Page up</p>
			<p>D or PAGE DOWN : Page down</p>
			<p>HOME : Top</p>
			<p>END : Bottom</p>
			<p>SPACE or RETURN : Selection</p>
			<p>ESC : Close menu</p>
		</li>
		<li class="slide-menu-item">
			<h3>Custom</h3>
			<p>I : ../index.html</p>
		</li></ul>` }
	],
  	themes: [// Specifies the themes that will be available in the themes menu panel. Set to 'true' to show the themes menu panel with the default themes list. 
		{ name: '########## light ##########', theme: 'themes/css/reveal-fred-light.css' },
		{ name: 'containers', theme: 'themes/css/reveal-fred-container.css' },
		{ name: 'security', theme: 'themes/css/reveal-fred-security.css' },
		{ name: '########## dark ##########', theme: 'themes/css/reveal-fred-dark.css' },
		{ name: 'matrix', theme: 'themes/css/reveal-fred-matrix-dark.css' },
		{ name: 'containers-dark', theme: 'themes/css/reveal-fred-container-dark.css' }]
}