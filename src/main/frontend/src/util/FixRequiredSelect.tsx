import React from "react";
import PropTypes from "prop-types";

type Props = {
    value: any,
    onChange: any
}

const noop = () => {
    // no operation (do nothing real quick)
};

class FixRequiredSelect extends React.Component<Props, {}> {
    state = {
        value: this.props.value || ""
    };

    selectRef = null;
    setSelectRef = (ref: any) => {
        this.selectRef = ref;
    };

    onChange = (value : any, actionMeta: any) => {
        this.props.onChange(value, actionMeta);
        this.setState({ value });
    };

    getValue = () => {
        if (this.props.value != undefined) {
            return this.props.value;
        }
        return this.state.value || "";
    };

    render() {
        // @ts-ignore
        const { SelectComponent, required, ...props } = this.props;
        // @ts-ignore
        const { isDisabled } = this.props;
        const enableRequired = !isDisabled;

        return (
            <div className={"mb-3 col-sm-8 form-control-sm pr-0 pl-0"}>
                <SelectComponent
                    {...props}
                    ref={this.setSelectRef}
                    onChange={this.onChange}
                />
                {enableRequired && (
                    <input
                        tabIndex={-1}
                        autoComplete="off"
                        style={{
                            opacity: 0,
                            width: "100%",
                            height: 0,
                            position: "absolute"
                        }}
                        value={this.getValue()}
                        onChange={noop}
                        // @ts-ignore
                        onFocus={() => this.selectRef.focus()}
                        required={required}
                    />
                )}
            </div>
        );
    }
}


// @ts-ignore
FixRequiredSelect.defaultProps = {
    onChange: noop
};

// @ts-ignore
FixRequiredSelect.protoTypes = {
    // react-select component class (e.g. Select, Creatable, Async)
    selectComponent: PropTypes.func.isRequired,
    onChange: PropTypes.func,
    required: PropTypes.bool
};

export default FixRequiredSelect;
