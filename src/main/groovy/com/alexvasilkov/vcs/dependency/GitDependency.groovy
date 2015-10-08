package com.alexvasilkov.vcs.dependency

import org.gradle.api.GradleException
import org.gradle.api.initialization.ProjectDescriptor

class GitDependency extends VcsDependency {

    final String remote
    final String commit

    GitDependency(ProjectDescriptor project, Map map) {
        super(project, map)
        remote = map.remote
        commit = map.commit
    }

    @Override
    void check() {
        super.check()
        if (!commit) throw new GradleException("Repo 'commit' was not specified")
    }

    @Override
    File getProjectDir() {
        return path == null ? repoDir : new File(repoDir, path)
    }

    @Override
    void checkEquals(VcsDependency d) {
        super.checkEquals(d)

        GitDependency g = (GitDependency) d;

        if (g.remote != remote) throwEqualCheckFail('remote', remote, g.remote)
        if (g.commit != commit) throwEqualCheckFail('commit', commit, g.commit)
    }

}
