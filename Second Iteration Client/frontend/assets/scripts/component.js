class Component {
    constructor(source) {
        if (source == undefined) {
            throw new Error("Can't be called directly!")
        }
        this.source = source
        this.nodes = []
    }

    append(nodeId, parentId) {
        if (this.nodes == undefined)
            throw new Error("No nodes were generated!")
        if (this.nodes[nodeId.toString()] == undefined)
            throw new Error(`Node ${nodeId} was not generated!`)
        document.getElementById(parentId).appendChild(this.nodes[nodeId.toString()])
    }

    generateNode(strings, nodeId, nodeClass) {
        let node = document.createElement('div')
        node.setAttribute('class', this.componentClass)
        node.setAttribute('id', nodeId)
        if(nodeClass != undefined)
            nodeClass.split('.').forEach( c => {
                node.classList.add(c)
            })
        let mSource = this.source.toString()
        // console.log(mSource)
        strings.forEach(element => {
            mSource = mSource.replace('---%%%---', element)
        });
        // console.log(mSource)
        node.innerHTML = mSource
        this.nodes[nodeId.toString()] = node
    }

    static async importSource(sourceURL) {
        let source = ''
        let response = await fetch(sourceURL)
        source = await response.text()
        return source
    }

    static async build(sourceURL) {
        let source = await Component.importSource(sourceURL)
        let result = new Component(source)
        result.componentClass =
            sourceURL.match(new RegExp("/components/" + "(.*)" + ".html"))[1]
        return result
    }
}