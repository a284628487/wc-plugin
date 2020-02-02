package com.ccf.wc.plugin

import com.android.build.api.transform.Context
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import org.gradle.api.Project

class WcTransform extends Transform {

    final static String TAG = "WcTransform"

    private Project project

    public WcTransform(Project project) {
        this.project = project
        println("${TAG}.init")
    }

    @Override
    String getName() {
        return "${TAG}"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        QualifiedContent.ContentType type = QualifiedContent.DefaultContentType.CLASSES
        return new HashSet<QualifiedContent.ContentType>(Arrays.asList(type))
    }

    @Override
    Set<QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(Context context, Collection<TransformInput> inputs,
                   Collection<TransformInput> referencedInputs,
                   TransformOutputProvider outputProvider,
                   boolean isIncremental) throws IOException, TransformException, InterruptedException {
        println("${TAG}.transform")
        for (TransformInput input : inputs) {
            println("inputs: ${input}")
        }
        for (TransformInput input : referencedInputs) {
            println("referencedInputs: ${input}")
        }
    }
    // :app:transformClassesWithWcTransformForRelease
    // WcTransform.transform
}
