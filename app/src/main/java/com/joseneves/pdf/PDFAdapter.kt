package com.joseneves.pdf

import android.content.Context
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.CancellationSignal
import android.os.ParcelFileDescriptor
import android.print.PageRange
import android.print.PrintAttributes
import android.print.PrintDocumentAdapter
import android.print.PrintDocumentInfo
import android.print.pdf.PrintedPdfDocument
import android.view.View
import java.io.FileOutputStream
import java.io.IOException

class PDFAdapter(internal var context: Context, internal var view: View) :
    PrintDocumentAdapter() {
    private var pageHeight: Int = 842
    private var pageWidth: Int = 598
    var myPdfDocument: PdfDocument? = null

    override fun onLayout(
        oldAttributes: PrintAttributes,
        newAttributes: PrintAttributes,
        cancellationSignal: CancellationSignal,
        callback: LayoutResultCallback,
        metadata: Bundle
    ) {
        myPdfDocument = PrintedPdfDocument(context, newAttributes)

        pageHeight = view.height
        pageWidth = view.width

        if (cancellationSignal.isCanceled) {
            callback.onLayoutCancelled()
            return
        }

        val builder = PrintDocumentInfo.Builder("print_output.pdf")
            .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
            .setPageCount(1)

        val info = builder.build()
        callback.onLayoutFinished(info, true)
    }

    override fun onWrite(
        pageRanges: Array<PageRange>,
        destination: ParcelFileDescriptor,
        cancellationSignal: CancellationSignal,
        callback: WriteResultCallback
    ) {
        val newPage = PdfDocument.PageInfo.Builder(
            pageWidth,
            pageHeight, 1
        ).create()

        val page = myPdfDocument?.startPage(newPage)

        if (cancellationSignal.isCanceled) {
            callback.onWriteCancelled()
            myPdfDocument?.close()
            myPdfDocument = null
            return
        }

        view.draw(page!!.canvas)

        myPdfDocument?.finishPage(page)

        try {
            myPdfDocument?.writeTo(
                FileOutputStream(
                    destination.fileDescriptor
                )
            )
        } catch (e: IOException) {
            callback.onWriteFailed(e.toString())
            return
        } finally {
            myPdfDocument?.close()
            myPdfDocument = null
        }

        callback.onWriteFinished(pageRanges)
    }

}