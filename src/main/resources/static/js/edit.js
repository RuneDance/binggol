$(document).ready(function () {
    init()/*.fullscreen()*/;
});

function init() {
    return editormd("md_edit", {
        width: "90%",
        height: 650,
        syncScrolling: "single",
        path: "../plugs/editor.md-1.5.0/lib/",
        placeholder: "",
        tex: true,
        autoLoadModules: false
    });
}

/**
 * 白色皮肤
 */
function add_white_theme() {
    $('body').css('background', '#ededed');
    $('#md_edit').css('box-shadow', '0px 0px 14px #302f2f');
    init().setTheme('default');
    init().setEditorTheme('default');
    init().setPreviewTheme('default');
}

/**
 * 黑色皮肤
 */
function add_black_theme() {
    $('body').css('background', '#f5f5d5');
    $('#md_edit').css('box-shadow', '0px 0px 14px 1px #302f2f');
    init().setTheme('dark');
    init().setEditorTheme('pastel-on-dark');
    init().setPreviewTheme('dark');
}

function save_markdown() {
    init().getMarkdown();
}