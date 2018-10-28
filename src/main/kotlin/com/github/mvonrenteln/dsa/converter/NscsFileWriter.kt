package com.github.mvonrenteln.dsa.converter

import org.apache.velocity.VelocityContext

class NscsFileWriter : HtmlFileWriter("NSCs") {

    override fun writeDataInternal(gruppenDaten: GruppenDaten, nscs: List<Nsc>) {
        val context = VelocityContext().apply {
            put("nscs", nscs)
            put("containerTyp", "container")
            put("sidebar", false)
        }
        NSC_TEMPLATE.merge(context, writer)

    }
}