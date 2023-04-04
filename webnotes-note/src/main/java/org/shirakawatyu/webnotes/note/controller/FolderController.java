package org.shirakawatyu.webnotes.note.controller;

import org.shirakawatyu.webnotes.common.R;
import org.shirakawatyu.webnotes.note.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FolderController {
    @Autowired
    FolderService folderService;
    @PostMapping("/api/folder/create")
    public R getAllFolder(@RequestParam String name) {
        return folderService.createFolder(name);
    }

    @PostMapping("/api/folder/delete")
    public R deleteFolder(@RequestParam int id) {
        return folderService.deleteFolder(id);
    }
}
