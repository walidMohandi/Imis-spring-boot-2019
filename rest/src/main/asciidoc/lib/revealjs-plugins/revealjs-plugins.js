{ src: 'lib/revealjs-plugins/menu/menu.js', async: true },
{ src: 'lib/revealjs-plugins/chalkboard/chalkboard.js', async: true },
{ src: 'lib/revealjs-plugins/title-footer/title-footer.js', callback: function() { title_footer.initialize(); } },//not async because it may then not appear
{ src: 'lib/revealjs-plugins/notes-pointer/notes-pointer.js', async: true },
{ src: 'lib/revealjs-plugins/spotlight/spotlight.js' }, // does not work with current version of reveal.js
{ src: 'lib/revealjs-plugins/skip-fragments/skip-fragments.js' }